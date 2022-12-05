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

public class AddTableSaleController {

    String id, nom;
    List listId, listNom;
    List<String> id_LIST = new ArrayList<>();
    List<String> nom_LIST = new ArrayList<>();
    String GetNom, strNom;

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
    private TextField id_price;

    @FXML
    private TextField id_sum;

    @FXML
    void ButtonAdd(ActionEvent event) {
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

        strNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
        String idNomen = id_LIST.get(Integer.parseInt(strNom));
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.nomenklatyra_prodasha(id_nomenklatyra, kolichestvo_prodasha, price_prodasha, summa_prodasha) VALUES('" + idNomen + "','" + id_amount.getText() + "','" + id_price.getText() + "', '" + id_sum.getText() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
        //tableView.refresh();

    }

    @FXML
    void initialize() {
        ComboBoxTableSale();
    }

    public void ComboBoxTableSale(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
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
                nom_LIST.add(nom);

                nom_LIST.indexOf(nom_LIST);

                System.out.println("ВЫбранный элемент "+ listNom.get(0));
                id_comboBoxNomenclature.getItems().addAll(listNom);
                id_comboBoxNomenclature.getSelectionModel().select(0);

                GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

                strNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
            }
            System.out.println(nom_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strNom)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
/*
    public void getSum(){
        double sum = 0;

            sum = Double.parseDouble(id_amount.getText()) * Double.parseDouble(id_price.getText());

        id_sum.getText() = Double.toString(sum);

    }*/

}