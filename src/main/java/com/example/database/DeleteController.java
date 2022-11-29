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
import javafx.stage.Stage;

public class DeleteController {

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
       /* try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("Delete from public.izmerenie where id_izmerenie = );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        Stage stage = (Stage) id_buttonNoDelete.getScene().getWindow();
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
/*
    public void buttonDelete(ActionEvent actionEvent) {




        Stage stage = (Stage) id_buttonDelete.getScene().getWindow();
        stage.close();
    }*/
}

