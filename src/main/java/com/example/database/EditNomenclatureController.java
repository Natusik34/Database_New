package com.example.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EditNomenclatureController {

    String nom;
    String id;
    List listId;
    List listUnit;
    String str;
    List<String>id_LIST = new ArrayList<>();
    List<String>NAM_LIST = new ArrayList<>();
    String GetValue;
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

                id = listRow.get(0);
                nom = listRow.get(1);

                listId = new ArrayList<>(Collections.singleton(id));
                listUnit = new ArrayList<>(Collections.singleton(nom));

                id_LIST.add(id);
                NAM_LIST.add(nom);

                NAM_LIST.indexOf(NAM_LIST);

                System.out.println("ВЫбранный элемент "+ listUnit.get(0));
                id_comboBoxUnit.getItems().addAll(listUnit);
                id_comboBoxUnit.getSelectionModel().select(0);

                GetValue = String.valueOf(id_comboBoxUnit.getSelectionModel().getSelectedIndex());

                str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
            }
            System.out.println(NAM_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(str)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ButtonAdd(ActionEvent actionEvent) {
        GetValue = String.valueOf(id_comboBoxUnit.getSelectionModel().getSelectedIndex());

        str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
String idIzmerenie = id_LIST.get(Integer.parseInt(str));
     //   System.out.println();
          try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.nomenklatyra(naimenovanie, id_izmerenie) VALUES('" + id_name.getText() + "','" + idIzmerenie + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }
}

