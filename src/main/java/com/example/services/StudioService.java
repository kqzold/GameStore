package com.example.services;

import com.example.model.Studio;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;

import java.util.List;

public class StudioService implements Service<Studio> {

    private final AppHelper<Studio> appHelperStudio;
    private final FileRepository<Studio> storage;
    private final String fileName = "studios.txt";

    public StudioService(AppHelper<Studio> appHelperStudio, FileRepository<Studio> storageStudio) {
        this.appHelperStudio = appHelperStudio;
        this.storage = storageStudio;
    }

    @Override
    public boolean add() {
        try {
            Studio studio = appHelperStudio.create();
            if(studio == null) {return false;}
            storage.save(studio,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Studio entity) {
        return false;
    }

    @Override
    public boolean remove(Studio entity) {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperStudio.printList(this.list());
    }

    @Override
    public List<Studio> list() {
        return storage.load(fileName);
    }
}