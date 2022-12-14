package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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

public class EditSaleController {

    String id, cklad;
    List listId, listCklad;
    List<String>id_LIST = new ArrayList<>();
    List<String>cklad_LIST = new ArrayList<>();
    String GetCklad, strCkl;

    DataSingleton dataS = DataSingleton.getInstance();
    String idSale;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private DatePicker id_saleDate;

    @FXML
    private ComboBox<?> id_warehouse;

    @FXML
    void initialize() {
        ComboBoxSale();
        idSale = dataS.getIdSale();
        //id_editName.setText("Привет");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.prodasha where id_prodasha = '" + idSale + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_invoiceNumber.setText(row.get(1));
                id_saleDate.setValue(LocalDate.parse(row.get(2)));
                id_warehouse.getValue();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ButtonEdit(ActionEvent actionEvent) {
        DBConnection dbConnection = new DBConnection();
        GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());

        strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));

        String idCklad = id_LIST.get(Integer.parseInt(strCkl));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.prodasha SET  nomer_nakladnoi= '"+id_invoiceNumber.getText()+"', data_prodasha= '"+id_saleDate.getValue()+"', id_cklad= '"+idCklad+"'  WHERE id_prodasha='"+idSale+"' ;");
        statement.close();
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();
    }


    public void ComboBoxSale(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.cklad");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                id = listRow.get(0);
                cklad = listRow.get(1);

                listId = new ArrayList<>(Collections.singleton(id));
                listCklad = new ArrayList<>(Collections.singleton(cklad));

                id_LIST.add(id);
                cklad_LIST.add(cklad);

                cklad_LIST.indexOf(cklad_LIST);

                System.out.println("ВЫбранный элемент "+ listCklad.get(0));
                id_warehouse.getItems().addAll(listCklad);
                id_warehouse.getSelectionModel().select(0);

                GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());

                strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


