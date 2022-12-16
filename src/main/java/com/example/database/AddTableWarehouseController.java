package com.example.database;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTableWarehouseController {

    String idWareh, wareh, nom,idNom;

    List listIdWareh, listWareh, listNom, listIdNom;

    List<String>id_LISTWareh = new ArrayList<>();
    List<String>wareh_LIST = new ArrayList<>();
    List<String>nom_LIST = new ArrayList<>();
    List<String>id_LISTNom = new ArrayList<>();

    String GetWareh, GetNom, strWareh, srtNom;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_amount;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private ComboBox<?> id_comboBoxNomenclature;

    @FXML
    private ComboBox<?> id_comboBoxWarehouse;

    @FXML
    void ButtonAdd(ActionEvent event) {
        DBConnection dbConnection = new DBConnection();
        GetWareh = String.valueOf(id_comboBoxWarehouse.getSelectionModel().getSelectedIndex());
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

        strWareh = String.valueOf(wareh_LIST.indexOf(wareh_LIST.get(Integer.parseInt(GetWareh))));
        srtNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));

        String idWarehouse = id_LISTWareh.get(Integer.parseInt(strWareh));
        String idNomenclature = id_LISTNom.get(Integer.parseInt(srtNom));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.tovar_cklad(id_cklad, id_nomenklatyra, kolichestvo_cklad) VALUES('" + idWarehouse + "', '" + idNomenclature + "','" + id_amount.getText() + "')");
       statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        ComboBoxWarehouse();
        ComboBoxNomenclature();


    }

    public void ComboBoxWarehouse(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.cklad");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idWareh = listRow.get(0);
                wareh = listRow.get(1);
                //post = listRow.get(2);

                listIdWareh = new ArrayList<>(Collections.singleton(idWareh));
                listWareh = new ArrayList<>(Collections.singleton(wareh));
                //listPost = new ArrayList<>(Collections.singleton(post));

                id_LISTWareh.add(idWareh);
                wareh_LIST.add(wareh);
                //post_LIST.add(post);

                wareh_LIST.indexOf(wareh_LIST);
                //post_LIST.indexOf(post_LIST);

                System.out.println("ВЫбранный элемент "+ listWareh.get(0));
                //System.out.println("ВЫбранный элемент "+ listPost.get(0));

                id_comboBoxWarehouse.getItems().addAll(listWareh);
                //id_supplier.getItems().addAll(listPost);

                id_comboBoxWarehouse.getSelectionModel().select(0);
                //id_supplier.getSelectionModel().select(0);

                GetWareh = String.valueOf(id_comboBoxWarehouse.getSelectionModel().getSelectedIndex());
                //GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

                strWareh = String.valueOf(wareh_LIST.indexOf(wareh_LIST.get(Integer.parseInt(GetWareh))));
                //srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(wareh_LIST.get(0));
            System.out.println(id_LISTWareh.get(Integer.parseInt(strWareh)));
           /* System.out.println(post_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(srtPost)));*/

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboBoxNomenclature(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.nomenklatyra");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idNom = listRow.get(0);
                //cklad = listRow.get(1);
                nom = listRow.get(1);

                listIdNom = new ArrayList<>(Collections.singleton(idNom));
                //listCklad = new ArrayList<>(Collections.singleton(cklad));
                listNom = new ArrayList<>(Collections.singleton(nom));

                id_LISTNom.add(idNom);
                //cklad_LIST.add(cklad);
                nom_LIST.add(nom);

                //cklad_LIST.indexOf(cklad_LIST);
                nom_LIST.indexOf(nom_LIST);

                //System.out.println("ВЫбранный элемент "+ listCklad.get(0));
                System.out.println("ВЫбранный элемент "+ listNom.get(0));

                //id_warehouse.getItems().addAll(listCklad);
                id_comboBoxNomenclature.getItems().addAll(listNom);

                //id_warehouse.getSelectionModel().select(0);
                id_comboBoxNomenclature.getSelectionModel().select(0);

                //GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());
                GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

                //strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
                srtNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
            }
            con.close();
            statement.close();
            rs.close();
            /*System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));*/
            System.out.println(nom_LIST.get(0));
            System.out.println(id_LISTNom.get(Integer.parseInt(srtNom)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}