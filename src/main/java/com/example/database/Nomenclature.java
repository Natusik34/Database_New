package com.example.database;

import javafx.beans.property.SimpleStringProperty;

public class Nomenclature {
    private SimpleStringProperty idNom;
    private SimpleStringProperty nameNom;
    private SimpleStringProperty idIzmNom;


    public Nomenclature(String idNom, String nameNom, String idIzmNom){
        this.idNom = new SimpleStringProperty(idNom);
        this.nameNom = new SimpleStringProperty(nameNom);
        this.idIzmNom = new SimpleStringProperty(idIzmNom);
    }

    public String getIdNom(){
        return idNom.get();
    }

    public String getNameNom(){
        return nameNom.get();
    }

    public String getIdIzmNom(){
        return idIzmNom.get();
    }
}
