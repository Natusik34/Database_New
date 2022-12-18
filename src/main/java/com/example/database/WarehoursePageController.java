package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class WarehoursePageController {

    Stage window;
    DataSingleton dataS =  DataSingleton.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonOutput;


    @FXML
    private Button id_nomenclature;

    @FXML
    private Button id_sale;

    @FXML
    private TextField id_search;

    @FXML
    private Button id_supplier;

    @FXML
    private Button id_supply;

    @FXML
    private TableView<ObservableList> id_tableWarehouse;


    @FXML
    private Button id_unitOfMeasurement;

    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataTable;

    @FXML
    protected void initialize() {
        UpdateWarehouse();

    }

    @FXML
    protected void buttonNomenclature() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("nomenclaturePage.fxml"));
        window = (Stage) id_nomenclature.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSupplier() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("supplierPage.fxml"));
        window = (Stage) id_supplier.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSupply() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("deliveryPage.fxml"));
        window = (Stage) id_supply.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSale() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("salePage.fxml"));
        window = (Stage) id_sale.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonUnitOfMeasurement() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("unitOfMeasurementPage.fxml"));
        window = (Stage) id_unitOfMeasurement.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void showAdd(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addWarehouse.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEdit(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editWarehouse.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDelete(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteWarehourse.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcell(MouseEvent mouseEvent) {
        ObservableList cklad = id_tableWarehouse.getSelectionModel().getSelectedItem();
        //  System.out.println(izm.get(0).toString());
        dataS.setIdWarehouse(cklad.get(0).toString());
        //System.out.println(dataS.getIdIzerenie());

        Peremennie.idCklad =  Integer.parseInt(cklad.get(0).toString()) ;
        Peremennie.nameCklad = cklad.get(1).toString();
        Peremennie.idNomWarehouse =  Integer.parseInt(cklad.get(2).toString()) ;
        Peremennie.amountWarehouse = cklad.get(3).toString();
    }


    public void showReset(ActionEvent actionEvent) {
        id_tableWarehouse.getColumns().clear();
        UpdateWarehouse();
    }


    public void UpdateWarehouse(){
        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from cklad");
            for(int i = 0; i < rs.getMetaData(

            ).getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableWarehouse.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableWarehouse.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Search(ActionEvent actionEvent) {
        id_tableWarehouse.getColumns().clear();
        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from cklad where naimenovanie like '"+id_search.getText()+"%'");
            for(int i = 0; i < rs.getMetaData(

            ).getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableWarehouse.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableWarehouse.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

