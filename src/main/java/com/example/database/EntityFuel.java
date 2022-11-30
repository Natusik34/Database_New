package com.example.database;

import javafx.beans.property.SimpleStringProperty;

public class EntityFuel {
    private SimpleStringProperty id;
    private SimpleStringProperty name;


    public EntityFuel(String id, String name){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public String getId(){
        return id.get();
    }

    public String getName(){
        return name.get();
    }
}
