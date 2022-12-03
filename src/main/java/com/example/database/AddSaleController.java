package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddSaleController {

    String id, cklad;
    List listId, listCklad;
    List<String>id_LIST = new ArrayList<>();
    List<String>cklad_LIST = new ArrayList<>();
    String GetCklad, strCkl;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonOutput;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private TextField id_saleDate;

    @FXML
    private TableView id_tableItemOfSale;

    @FXML
    private ComboBox<?> id_warehouse;

    private ObservableList<ObservableList> data;

    @FXML
    protected void initialize() {
        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from prodasha");
            for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableItemOfSale.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableItemOfSale.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ComboBoxSale();

    }

    public void AddSale(ActionEvent actionEvent) {
        GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());

        strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
        String idCklad = id_LIST.get(Integer.parseInt(strCkl));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.prodasha(nomer_nakladnoi, data_prodasha, id_cklad) VALUES('" + id_invoiceNumber.getText() + "','" + id_saleDate.getText() + "','" + idCklad + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }

    public void ComboBoxSale(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
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
            System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

