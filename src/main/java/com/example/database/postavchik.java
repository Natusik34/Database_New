package com.example.database;

public class postavchik {
    String id_columnName, id_columnPhoneNumber, id_columnINN, id_columnKPP;

    public void setId_columnName(String id_columnName){
        this.id_columnName = id_columnName;
    }

    public void setId_columnPhoneNumber(String id_columnPhoneNumber){
        this.id_columnPhoneNumber = id_columnPhoneNumber;
    }

    public void setId_columnINN(String id_columnINN){
        this.id_columnINN = id_columnINN;
    }

    public void setId_columnKPP(String id_columnKPP){
        this.id_columnKPP = id_columnKPP;
    }

    public String getId_columnName(){
        return id_columnName;
    }

    public String getId_columnPhoneNumber(){
        return id_columnPhoneNumber;
    }

    public String getId_columnINN(){
        return id_columnINN;
    }

    public String getId_columnKPP(){
        return id_columnKPP;
    }

    public postavchik(String id_columnName, String id_columnPhoneNumber, String id_columnINN, String id_columnKPP){
        this.id_columnName = id_columnName;
        this.id_columnPhoneNumber = id_columnPhoneNumber;
        this.id_columnINN = id_columnINN;
        this.id_columnKPP = id_columnKPP;
    }
}
