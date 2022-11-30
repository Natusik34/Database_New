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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddDeliveryController {

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
    private TextField id_deliveryDate;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private ComboBox<?> id_supplier;

    @FXML
    private TableView id_tableDeliveryNomenclature;

    @FXML
    private ComboBox<?> id_warehouse;

    private ObservableList<ObservableList> data;

    @FXML
    void initialize() {
        data = FXCollections.observableArrayList();
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
                data.add(row);
            }
            id_tableDeliveryNomenclature.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void buttonAdd(ActionEvent actionEvent) {
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

    public void buttonEdit(ActionEvent actionEvent) {
    }

    public void buttonDelete(ActionEvent actionEvent) {
    }

    public void ButtonAdd(ActionEvent actionEvent) {
    }
}

