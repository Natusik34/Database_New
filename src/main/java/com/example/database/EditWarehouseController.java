package com.example.database;

import java.io.IOException;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditWarehouseController {

    DataSingleton dataS = DataSingleton.getInstance();
    String idWarehouse;

    String nom;
    String id;
    List listId;
    List listNom;
    String str;
    List<String>id_LIST = new ArrayList<>();
    List<String>NAM_LIST = new ArrayList<>();
    String GetValue;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button id_buttonEdit;

    @FXML
    private ComboBox<?> id_comboBoxNomenclarure;

    @FXML
    private TextField id_amount;

    @FXML
    private TextField id_name;

    @FXML
    void initialize() {
        ComboBoxNomenclature();
        idWarehouse = dataS.getIdWarehouse();
        //id_editName.setText("Привет");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.cklad where id_cklad = '" + idWarehouse + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_name.setText(row.get(1));
                id_comboBoxNomenclarure.getValue();
                id_amount.setText(row.get(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ButtonEdit(ActionEvent actionEvent) {
        DBConnection dbConnection = new DBConnection();
        GetValue = String.valueOf(id_comboBoxNomenclarure.getSelectionModel().getSelectedIndex());
        str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
        String idNomenclature = id_LIST.get(Integer.parseInt(str));
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.cklad SET  naimenovanie='"+id_name.getText()+"', id_nomenklatyra='"+idNomenclature+"', kolichestvo_cklad='"+id_amount.getText()+"' WHERE id_cklad='"+idWarehouse+"' ;");
        statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();
    }

    public void ComboBoxNomenclature(){
        DBConnection dbConnection = new DBConnection();
        //ObservableList<ObservableList> list = FXCollections.observableArrayList();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.nomenklatyra");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                id = listRow.get(0);
                nom = listRow.get(1);

                listId = new ArrayList<>(Collections.singleton(id));
                listNom = new ArrayList<>(Collections.singleton(nom));

                id_LIST.add(id);
                NAM_LIST.add(nom);

                NAM_LIST.indexOf(NAM_LIST);

                System.out.println("ВЫбранный элемент "+ listNom.get(0));
                id_comboBoxNomenclarure.getItems().addAll(listNom);
                id_comboBoxNomenclarure.getSelectionModel().select(0);

                GetValue = String.valueOf(id_comboBoxNomenclarure.getSelectionModel().getSelectedIndex());

                str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(NAM_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(str)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


