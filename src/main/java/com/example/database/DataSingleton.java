package com.example.database;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    String idIzerenie;
    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return  instance;
    }

    public String getIdIzerenie() {
        return idIzerenie;
    }

    public void setIdIzerenie(String idIzerenie) {
        this.idIzerenie = idIzerenie;
    }
}
