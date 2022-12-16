package com.example.database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_INN;

    @FXML
    private TextField id_KPP;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private TextField id_name;

    @FXML
    private TextField id_phoneNumber;

    @FXML
    void initialize() {


    }

    public void buttonAddSupplier(ActionEvent actionEvent) {
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.postavchik(naimenovanie, nomer_telefona, \"INN\", \"KPP\") VALUES('" + id_name.getText() + "', '" + id_phoneNumber.getText() + "', '" + id_INN.getText() + "', '" + id_KPP.getText() + "')");
        statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }
}


