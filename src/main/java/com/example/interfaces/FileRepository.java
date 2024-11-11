package com.example.interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public interface FileRepository<T> {
    default void save(T entity,String fileName){
        List<T> list = this.load(fileName);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(entity);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Do not have file with this name: "+fileName);
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    };
    default void saveAll(List<T> entities,String fileName){
        if(entities == null){entities = new ArrayList<>();}
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Do not have file with this name: "+fileName);
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    };
    default List<T> load(String fileName){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Do not have file with this name: "+fileName);
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return new ArrayList<>();
    }
}