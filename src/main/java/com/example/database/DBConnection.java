package com.example.database;

import java.io.IOException;
import java.sql.*;

public class DBConnection {

    public Connection connection = null;
    public String jdbcURL = "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka";
    public String username;
    public String password;
    //String username = "Vasiltsova";
    //String password = "Vasiltsova";

    public void Connection() throws IOException, SQLException {

        //com.example.database.DBConnection con1 = new com.example.database.DBConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova","Vasiltsova");

        try{
            //Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            connection = DriverManager.getConnection(jdbcURL, "Vasiltsova", "Vasiltsova");
            System.out.println("Connected");
            //return connection;

        } catch (SQLException e){
            System.out.println("Error in connection");
            e.printStackTrace();
            //return null;
        }
    }

    public ResultSet gettable(String sql){
        try{
            //sql = "Select * from izmerenie";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            return rs;
        }
        catch (Exception e){
            return null;
        }

    }

    //добавление
/*
    public static void Insert(String insert){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
    /*
    public static void Insert() {
        //добавление строки в таблицу измерение
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertIzmenenie = statement.executeUpdate("INSERT INTO public.izmerenie(naimenovanie) VALUES ('кг')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление строки в таблицу склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertCklad = statement.executeUpdate("INSERT INTO public.cklad(naimenovanie) VALUES ('ДТ')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление строки в таблицу номенклатура
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertNomenklatyra = statement.executeUpdate("INSERT INTO public.nomenklatyra(naimenovanie, id_izmerenie) VALUES ('Пропан', '1')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление строки в таблицу поставщик
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertPostavchik = statement.executeUpdate("INSERT INTO public.postavchik(naimenovanie, nomer_telefona, \"INN\", \"KPP\") VALUES ('Ромашка', '89100932155', '123456789', '123456789')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление в таблицу поставка
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertPostavka = statement.executeUpdate("INSERT INTO public.postavka(nomer_nakladnoi, data_postavki, id_cklad, id_postavchik) VALUES ('21pghgjkddlcdm', '08.07.2021', '1', '1')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление в таблицу продажа
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertProdasha = statement.executeUpdate("INSERT INTO public.prodasha(nomer_nakladnoi, data_prodasha, id_cklad) VALUES ('21pghgjkddlcdm', '08.07.2021', '1')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление в таблицу номенклатура поставки
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertNomenklatyraPostavka = statement.executeUpdate("INSERT INTO public.nomenklatyra_postavka(id_postavka, id_nomenklatyra, kolichestvo_postavka, price_postavka, summa_postavka) VALUES ('1', '1', '4', '50', '200')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление в таблицу номенклатура продажи
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int insertNomenklatyraProdasha = statement.executeUpdate("INSERT INTO public.nomenklatyra_prodasha(id_prodasha, id_nomenklatyra, kolichestvo_prodasha, price_prodasha, summa_prodasha) VALUES ('1', '1', '4', '50', '200')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //добавление в таблицу товар склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int inserTovarCklad = statement.executeUpdate("INSERT INTO public.tovar_cklad(id_cklad, id_nomenklatyra, kolichestvo_cklad, price_cklad) VALUES ('1', '1', '4', '50')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */
/*
    //обновление данных
    public  static void Update(){
        //обновление данных в таблице единица измерения
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateIzmenenie = statement.executeUpdate("UPDATE public.izmerenie SET naimenovanie = 'литры' WHERE id_izmerenie = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateCklad = statement.executeUpdate("UPDATE public.cklad SET naimenovanie = 'Пропан' WHERE id_cklad = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице номенклатура
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateNomenklatyra = statement.executeUpdate("UPDATE public.nomenklatyra SET naimenovanie = 'Аи-92', id_izmerenie = '2' WHERE id_nomenklatyra = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице поставщик
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updatePostavchik = statement.executeUpdate("UPDATE public.postavchik SET naimenovanie = 'Лес рук', nomer_telefona = '89123456789' , \"INN\" = '5379123086' , \"KPP\" = '582396459'  WHERE id_postavchik = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице поставка
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updatePostavka = statement.executeUpdate("UPDATE public.postavka SET nomer_nakladnoi = '546456654fbhxv', data_postavki = '09.06.2022' , id_cklad = '2' , id_postavchik = '2'  WHERE id_postavka = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице продажа
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateProdasha = statement.executeUpdate("UPDATE public.prodasha SET nomer_nakladnoi = '546456654fbhxv', data_prodasha = '09.06.2022' , id_cklad = '2'  WHERE id_prodasha = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице номенклатура поставки
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateNomenklatyraPostavka = statement.executeUpdate("UPDATE public.nomenklatyra_postavka SET id_postavka = '2', id_nomenklatyra = '2' , kolichestvo_postavka = '2', price_postavka = '40', summa_postavka = '80' WHERE id_nomenklatyra_postavka = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице номенклатура продажи
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateNomenklatyraProdasha = statement.executeUpdate("UPDATE public.nomenklatyra_prodasha SET id_prodasha = '2', id_nomenklatyra = '2' , kolichestvo_prodasha = '2', price_prodasha = '40', summa_prodasha = '80' WHERE id_nomenklatyra_prodasha = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //обновление данных в таблице товар склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int updateTovarCklad = statement.executeUpdate("UPDATE public.tovar_cklad SET id_cklad = '2', id_nomenklatyra = '2' , kolichestvo_cklad = '2', price_cklad = '40' WHERE id_tovar_cklad = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    //удаление
    public  static void Delete(){
/*
//сделано

        //удаление данных в таблице единица измерения
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteIzmenenie = statement.executeUpdate("DELETE FROM public.izmerenie WHERE id_izmerenie = '3'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//сделано
        //удаление данных в таблице склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteCklad = statement.executeUpdate("DELETE FROM public.cklad WHERE id_cklad = '3'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //сделано
        //удаление данных в таблице номенклатура
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteNomenklatyra = statement.executeUpdate("DELETE FROM public.nomenklatyra WHERE id_nomenklatyra = '2'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //сделано
        //удаление данных в таблице поставщик
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deletePostavchik = statement.executeUpdate("DELETE FROM public.postavchik WHERE id_postavchik = '3'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//сделано
        //удаление данных в таблице поставка
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deletePostavka = statement.executeUpdate("DELETE FROM public.postavka WHERE id_postavka = '2'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //удаление данных в таблице продажа
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteProdasha = statement.executeUpdate("DELETE FROM public.prodasha WHERE id_prodasha = '2'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//сделано
        //удаление данных в таблице номенклатура поставки
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteNomenklatyraPostavka = statement.executeUpdate("DELETE FROM public.nomenklatyra_postavka WHERE id_nomenklatyra_postavka = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //удаление данных в таблице номенклатура продажи
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteNomenklatyraProdasha = statement.executeUpdate("DELETE FROM public.nomenklatyra_prodasha WHERE id_nomenklatyra_prodasha = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //удаление данных в таблице товар склад
        try {
            Statement statement = com.example.database.DBConnection("Vasiltsova", "Vasiltsova", "jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka").createStatement();
            int deleteTovarCklad = statement.executeUpdate("DELETE FROM public.tovar_cklad WHERE id_tovar_cklad = '1'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }



    //изменение
//int ro = statement.executeUpdate("UPDATE izmerenie SET naimenovanie");
    //удаление
    //  int row = statement.executeUpdate("DELETE FROM izmerenie WHERE naimenovanie");
    //  connection.close();

}

