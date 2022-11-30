package com.example.database;

import java.net.URL;
import java.sql.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNomenclatureController {

    String nom;

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
        ComboBoxNomenclature();
    }

    public void ComboBoxNomenclature(){
        //ObservableList<ObservableList> list = FXCollections.observableArrayList();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.izmerenie");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }
                nom = listRow.get(0);
                List listUnit = new ArrayList<>(Collections.singleton(nom));
                List listId = new ArrayList<>(Collections.singleton(nom));
                //String id = lisrRow.get(0);
                id_comboBoxUnit.getItems().addAll(listUnit);
                id_comboBoxUnit.getSelectionModel().select(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ButtonAdd(ActionEvent actionEvent) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.nomenklatyra(naimenovanie, id_izmerenie) VALUES('" + id_name.getText() + "','" + id_comboBoxUnit.getValue() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }
}

