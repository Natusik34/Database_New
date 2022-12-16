package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {

    String login;
    String password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_entrance;

    @FXML
    private TextField id_login;

    @FXML
    private PasswordField id_password;

    @FXML
    private Button id_registration;

    @FXML
    void initialize() {
        id_registration.setOnAction(event -> {
            id_registration.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

        id_entrance.setOnAction(event -> {

            Authorization();
        });

    }

    public void ButtonAuthorization(ActionEvent actionEvent) {
    }

    public void Authorization(){
        boolean UserInOnOff = false;
        DBConnection con = new DBConnection();
        con.username=id_login.getText();
        con.password=id_password.getText();
        try {
            con.Connection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        if(con.checkCon.equals("Connected")){
            id_entrance.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("workingPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
    }

    public void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(window));

        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Hello Airline");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }
}
