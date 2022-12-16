package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditWarehouseController {

    DataSingleton dataS = DataSingleton.getInstance();
    String idWarehouse;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button id_buttonEdit;
    

    @FXML
    private TextField id_name;

    @FXML
    void initialize() {
        idWarehouse = dataS.getIdWarehouse();
        //id_editName.setText("Привет");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.cklad where id_cklad = '" + idWarehouse + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_name.setText(row.get(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ButtonEdit(ActionEvent actionEvent) {
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.cklad SET  naimenovanie='"+id_name.getText()+"' WHERE id_cklad='"+idWarehouse+"' ;");
        statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();
    }
}


