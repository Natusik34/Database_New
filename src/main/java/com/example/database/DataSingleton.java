package com.example.database;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    String idIzerenie;
    String idNomenclature;
    String idSupplier;
    String idWarehouse;
    //String idIzerenieFromNom;

    String idDelivery;
    String idDeliveryNomenclature;

    String idSale;
    String idSaleNomenclature;

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

    public String getIdNomenclature() {
        return idNomenclature;
    }

    public void setIdNomenclature(String idNomenclature) {
        this.idNomenclature = idNomenclature;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(String idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(String idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getIdDeliveryNomenclature() {
        return idDeliveryNomenclature;
    }

    public void setIdDeliveryNomenclature(String idDeliveryNomenclature) {
        this.idDeliveryNomenclature = idDeliveryNomenclature;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getIdSaleNomenclature() {
        return idSaleNomenclature;
    }

    public void setIdSaleNomenclature(String idSaleNomenclature) {
        this.idSaleNomenclature = idSaleNomenclature;
    }

}
