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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class NomenclaturePageController {

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
    private Button id_sale;

    @FXML
    private TextField id_search;

    @FXML
    private Button id_supplier;

    @FXML
    private Button id_supply;

    @FXML
    private TableView<ObservableList> id_tableNomenclature;

    @FXML
    private Button id_unitOfMeasurement;

    @FXML
    private Button id_warehouse;

    private ObservableList<ObservableList> data;

    @FXML
    protected void initialize() {
        UpdateTable();

    }

    public void showAdd(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addNomenclature.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEdit(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editNomenclature.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    protected void buttonUnitOfMeasurement() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("unitOfMeasurementPage.fxml"));
        window = (Stage) id_unitOfMeasurement.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void showDelete(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteNomenclature.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcell(MouseEvent mouseEvent) {
        ObservableList nom = id_tableNomenclature.getSelectionModel().getSelectedItem();
          //System.out.println(nom.get(0).toString());

        dataS.setIdNomenclature(nom.get(0).toString());
        //dataS.setIdIzerenieFromNom(nom.get(2).toString());

      //System.out.println(dataS.getIdNomenclature());
       // System.out.println(dataS.getIdIzerenieFromNom());

        Peremennie.idNom =  Integer.parseInt(nom.get(0).toString()) ;
        Peremennie.nameNom = nom.get(1).toString();
        Peremennie.idIzmNom =  Integer.parseInt(nom.get(2).toString()) ;
        Peremennie.price = nom.get(3).toString();

    }

    public void showReset(ActionEvent actionEvent) {
        id_tableNomenclature.getColumns().clear();
        UpdateTable();
    }

    public void UpdateTable(){
        data = FXCollections.observableArrayList();
        try {
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from nomenklatyra");
            for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableNomenclature.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableNomenclature.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}