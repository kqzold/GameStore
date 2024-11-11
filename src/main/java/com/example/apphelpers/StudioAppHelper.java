package com.example.apphelpers;

import java.util.List;

import com.example.interfaces.AppHelper;
import com.example.interfaces.Input;
import com.example.model.Studio;

public class StudioAppHelper implements AppHelper<Studio>, Input{

    public StudioAppHelper() {

    }

    @Override
    public Studio create() {
        try{ 
            Studio studio = new Studio();
            System.out.println("Enter the name of the studio: ");
            studio.setName(getString());   
            return studio;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<Studio> studios) {
        System.out.println("---------List of Studios---------");
        for(int i = 0; i < studios.size(); i++) {
            Studio studio = studios.get(i);
            System.out.println((i+1) + ". " + studio.getName());
        }
        return false;

    }

}
