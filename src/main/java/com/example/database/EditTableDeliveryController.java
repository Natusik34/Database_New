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

public class EditTableDeliveryController {

    String idDeliv, deliv, nom,idNom;

    List listIdDeliv, listDeliv, listNom, listIdNom;

    List<String>id_LISTDeliv = new ArrayList<>();
    List<String>deliv_LIST = new ArrayList<>();
    List<String>nom_LIST = new ArrayList<>();
    List<String>id_LISTNom = new ArrayList<>();

    String GetDeliv, GetNom, strDeliv, srtNom;

    DataSingleton dataS = DataSingleton.getInstance();
    String idNomenclatureDelivery;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_amount;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private ComboBox<?> id_comboBoxNomenclature;

    @FXML
    private ComboBox<?> id_comboBoxDelivery;

    @FXML
    private TextField id_price;

    @FXML
    private TextField id_sum;

    @FXML
    void ButtonEdit(ActionEvent event) {
        DBConnection dbConnection = new DBConnection();
        GetDeliv = String.valueOf(id_comboBoxDelivery.getSelectionModel().getSelectedIndex());
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());
        strDeliv = String.valueOf(deliv_LIST.indexOf(deliv_LIST.get(Integer.parseInt(GetDeliv))));
        srtNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
        String idDelivery = id_LISTDeliv.get(Integer.parseInt(strDeliv));
        String idNomenclature = id_LISTNom.get(Integer.parseInt(srtNom));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.nomenklatyra_postavka SET  id_postavka= '"+idDelivery+"',id_nomenklatyra = '"+idNomenclature+"', kolichestvo_postavka = '"+id_amount.getText()+"', price_postavka = '"+id_price.getText()+"', summa_postavka = '"+id_sum.getText()+"' WHERE id_nomenklatyra_postavka='"+idNomenclatureDelivery+"' ;");
        statement.close();
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        ComboBoxDelivery();
        ComboBoxNomenclature();

        id_amount.textProperty().addListener((observable, oldValue, newValue) -> {
            // String str = id_price.getText();
            // System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_price.getText());
            id_sum.setText(String.valueOf(str1));
        });
        id_price.textProperty().addListener((observable, oldValue, newValue) -> {
            //  String str = id_price.getText();
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_amount.getText());
            id_sum.setText(String.valueOf(str1));
        });


        idNomenclatureDelivery = dataS.getIdDeliveryNomenclature();
        //id_editName.setText("????????????");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.nomenklatyra_postavka where id_nomenklatyra_postavka = '" + idNomenclatureDelivery + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_comboBoxDelivery.getValue();
                id_comboBoxNomenclature.getValue();
                id_amount.setText(row.get(3));
                id_price.setText(row.get(4));
                id_sum.setText(row.get(5));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ComboBoxDelivery(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.postavka");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idDeliv = listRow.get(0);
                deliv = listRow.get(1);
                //post = listRow.get(2);

                listIdDeliv = new ArrayList<>(Collections.singleton(idDeliv));
                listDeliv = new ArrayList<>(Collections.singleton(deliv));
                //listPost = new ArrayList<>(Collections.singleton(post));

                id_LISTDeliv.add(idDeliv);
                deliv_LIST.add(deliv);
                //post_LIST.add(post);

                deliv_LIST.indexOf(deliv_LIST);
                //post_LIST.indexOf(post_LIST);

                System.out.println("?????????????????? ?????????????? "+ listDeliv.get(0));
                //System.out.println("?????????????????? ?????????????? "+ listPost.get(0));

                id_comboBoxDelivery.getItems().addAll(listDeliv);
                //id_supplier.getItems().addAll(listPost);

                id_comboBoxDelivery.getSelectionModel().select(0);
                //id_supplier.getSelectionModel().select(0);

                GetDeliv = String.valueOf(id_comboBoxDelivery.getSelectionModel().getSelectedIndex());
                //GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

                strDeliv = String.valueOf(deliv_LIST.indexOf(deliv_LIST.get(Integer.parseInt(GetDeliv))));
                //srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(deliv_LIST.get(0));
            System.out.println(id_LISTDeliv.get(Integer.parseInt(strDeliv)));
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

                //System.out.println("?????????????????? ?????????????? "+ listCklad.get(0));
                System.out.println("?????????????????? ?????????????? "+ listNom.get(0));

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
