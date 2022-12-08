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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class UnitOfMeasurementPageController {

    Stage window;
    DataSingleton dataS =  DataSingleton.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonReset;

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
    private TableView<ObservableList> id_tableUnitOfMeasurement;

    @FXML
    private Button id_warehouse;

    private ObservableList<ObservableList> data;



    @FXML
    protected void initialize() {

       /* data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from izmerenie");
            for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableUnitOfMeasurement.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableUnitOfMeasurement.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        UpdateTable();
    }

    public void UpdateTable(){
        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from izmerenie");
            for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableUnitOfMeasurement.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableUnitOfMeasurement.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    protected void buttonWarehouse() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("warehousePage.fxml"));
        window = (Stage) id_warehouse.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void showAdd(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addUnitOfMeasurement.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

     /*   Object source = actionEvent.getSource();
        //если нажата не кнопка - выходим из метода
        if(!(source instanceof Button)){
            return;
        }

        Button clickedButton = (Button) source;*/

    }

    public void showEdit(ActionEvent actionEvent) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("Select FROM public.izmerenie WHERE id_izmerenie = '" + Peremennie.id + "'");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editUnitOfMeasurement.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDelete(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

       // int selectedIndex = id_tableUnitOfMeasurement.getSelectionModel().getSelectionIndex();
       // id_tableUnitOfMeasurement.getItems().remove(selectedIndex);
    }


    public void getcell(MouseEvent mouseEvent) {
        ObservableList izm = id_tableUnitOfMeasurement.getSelectionModel().getSelectedItem();
      //  System.out.println(izm.get(0).toString());
        dataS.setIdIzerenie(izm.get(0).toString());
        System.out.println(dataS.getIdIzerenie());

        Peremennie.name = izm.get(1).toString();
        Peremennie.id =  Integer.parseInt(izm.get(0).toString()) ;
    }

    public void showReset(ActionEvent actionEvent) {
        id_tableUnitOfMeasurement.getColumns().clear();
        UpdateTable();
    }

    public void Search(ActionEvent actionEvent) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select public.cklad  where naimenovanie like '%"+id_search.getText()+"%' ");
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

