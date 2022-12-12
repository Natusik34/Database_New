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


        try {
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from users");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                System.out.println(row.get(0)+row.get(1)+row.get(2));
                if (row.get(1).equals(id_login.getText().trim()) && row.get(2).equals(id_password.getText().trim())) {
                    login = row.get(1);
                    password = row.get(2);
                    System.out.println("Найден логин: " + login + " Найден пароль: " + password);
                    UserInOnOff = true;
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

                    //Parent root = FXMLLoader.load(getClass().getResource("tabl_av.fxml"));
                   // mainwindow = (Stage) vxod.getScene().getWindow();
                   // mainwindow.setScene(new Scene(root1));

                } else System.out.println("Фу, лох, даже данные ввести правильно не можешь!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
}
