package com.example.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteWarehourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonNoDelete;

    @FXML
    void buttonDelete(ActionEvent event) {
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("DELETE FROM public.cklad WHERE id_cklad = " + Peremennie.idCklad + "");
        statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonDelete.getScene().getWindow();
        stage.close();

    }

    @FXML
    void buttonNoDelete(ActionEvent event) {
        Stage stage = (Stage) id_buttonNoDelete.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {


    }
}

