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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditNomenclatureController {

    String nom;
    String id;
    List listId;
    List listUnit;
    List listgetNomfromCB;
    String str;
    List<String>id_LIST = new ArrayList<>();
    List<String>NAM_LIST = new ArrayList<>();
    String GetValue;

    DataSingleton dataS = DataSingleton.getInstance();
    String idNomenclature;
    String str1;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private ComboBox<?> id_comboBoxUnit;

    @FXML
    private TextField id_name;

    @FXML
    void ButtonEdit(ActionEvent event) {
        GetValue = String.valueOf(id_comboBoxUnit.getSelectionModel().getSelectedIndex());
        str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
        String idIzmerenie = id_LIST.get(Integer.parseInt(str));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.nomenklatyra SET  naimenovanie= '"+id_name.getText()+"', '"+idIzmerenie+"' WHERE id_nomenklatyra='"+idNomenclature+"' ;");
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        ComboBoxNomenclature();
     //   getCB();
        idNomenclature = dataS.getIdNomenclature();
        //id_editName.setText("Привет");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.nomenklatyra where id_nomenklatyra = '" + idNomenclature + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_name.setText(row.get(1));
                id_comboBoxUnit.getValue();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
public void getCB(){
    try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("Select * from public.izmerenie where id_izmerenie ='"+dataS.getIdIzerenieFromNom()+"';");
        while(rs.next()){
            ObservableList<String> listRow = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                listRow.add(rs.getString(i));
            }

            nom = listRow.get(1);

            listgetNomfromCB = new ArrayList<>(Collections.singleton(nom));
            listgetNomfromCB.add(nom);

            listgetNomfromCB.indexOf(dataS.getIdIzerenieFromNom());



         //   id_comboBoxUnit.getItems().addAll(listUnit);


          //  id_comboBoxUnit.getSelectionModel().select(0);
           // GetValue = String.valueOf(id_comboBoxUnit.getSelectionModel().getSelectedIndex());

        //    str = String.valueOf(NAM_LIST.indexOf(NAM_LIST.get(Integer.parseInt(GetValue))));
        }System.out.println("ВЫбранный элемент "+  listgetNomfromCB);
     //   System.out.println(NAM_LIST.get(0));
      //  System.out.println(id_LIST.get(Integer.parseInt(str)));

       // str1 = dataS.getIdIzerenieFromNom();
       // id_comboBoxUnit.getSelectionModel().select(Integer.parseInt(str1));
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
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
                System.out.println(str);
            }
            id_comboBoxUnit.getSelectionModel().select(0);

            System.out.println(NAM_LIST);
            System.out.println(NAM_LIST.indexOf(dataS.getIdIzerenieFromNom()));
            System.out.println(NAM_LIST.indexOf(id_LIST.get(2)));
            System.out.println(NAM_LIST.indexOf(2));
            str1 = dataS.getIdIzerenieFromNom();
           // id_comboBoxUnit.getSelectionModel().select(GetValue);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

