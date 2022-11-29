package com.example.database;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddNomenclatureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private ComboBox<?> id_comboBoxUnit;

    @FXML
    private TextField id_name;

    @FXML
    void initialize() {


    }
   /*
    private void ComboBoxNomenclature(){
        ObservableList<ObservableList> list = FXCollections.observableArrayList();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select from public.izmerenie");
            while(rs.next()){
                list.add

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

}

