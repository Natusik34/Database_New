package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DeliveryPageController {

    Stage window;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonDeleteRow;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonInputRow;

    @FXML
    private Button id_buttonOutput;

    @FXML
    private Button id_buttonOutputRow;


    @FXML
    private Button id_nomenclature;

    @FXML
    private Button id_sale;

    @FXML
    private TextField id_search;

    @FXML
    private Button id_supplier;

    @FXML
    private TableView<ObservableList> id_tableDelivery;

    @FXML
    private TableView<ObservableList> id_tableDeliveryNomenclature;

    @FXML
    private Button id_unitOfMeasurement;

    @FXML
    private Button id_warehouse;

    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataTable;

    @FXML
    void initialize() {

        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from postavka");
            for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableDelivery.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableDelivery.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /////////////////////////////

        dataTable = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from nomenklatyra_postavka");
            for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableDeliveryNomenclature.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                dataTable.add(row);
            }
            id_tableDeliveryNomenclature.setItems(dataTable);
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

    public void showEdit(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdd(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcell(MouseEvent mouseEvent) {
        ObservableList deliv = id_tableDelivery.getSelectionModel().getSelectedItem();
        //  System.out.println(izm.get(0).toString());
        //dataS.setIdIzerenie(izm.get(0).toString());
        //System.out.println(dataS.getIdIzerenie());

        Peremennie.idDelivery =  Integer.parseInt(deliv.get(0).toString()) ;
        Peremennie.nomNakDelivery = deliv.get(1).toString();
        Peremennie.DataDelivery = deliv.get(2).toString();
        Peremennie.idCkladDelivery =  Integer.parseInt(deliv.get(3).toString()) ;
        Peremennie.idPostavDelivery =  Integer.parseInt(deliv.get(4).toString()) ;

    }

    public void showDelete(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcellTable(MouseEvent mouseEvent) {
        ObservableList delivTable = id_tableDeliveryNomenclature.getSelectionModel().getSelectedItem();
        //  System.out.println(izm.get(0).toString());
        //dataS.setIdIzerenie(izm.get(0).toString());
        //System.out.println(dataS.getIdIzerenie());

        Peremennie.idNomenclatureDeliveryTable =  Integer.parseInt(delivTable.get(0).toString()) ;
        Peremennie.idDeliveryTable =  Integer.parseInt(delivTable.get(1).toString()) ;
        Peremennie.idNomDelivery =  Integer.parseInt(delivTable.get(2).toString()) ;
        Peremennie.amountDelivery = delivTable.get(3).toString();
        Peremennie.priceDelivery = delivTable.get(4).toString();
        Peremennie.sumDelivery = delivTable.get(5).toString();
    }

    public void showEditRow(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editTableDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddRow(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addTableDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteRow(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteTableDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

