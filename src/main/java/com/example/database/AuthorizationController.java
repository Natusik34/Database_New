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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AuthorizationController {

    Stage window;

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

        id_entrance.setOnAction(event -> {

            Authorization();
        });

    }

    public void ButtonAuthorization(ActionEvent actionEvent) {
    }

    public void Authorization(){
        DBConnection con = new DBConnection();
        con.username=id_login.getText();
        con.password=id_password.getText();
        try {
            con.Connection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        if(con.checkCon.equals("Connected")) {
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
            stage.setResizable(false);
            stage.showAndWait();
        }else{ getWarning("Ошибка", "Неверный логин или пароль!!!");}
        }


    @FXML
    protected void ButtonRegistration() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        window = (Stage) id_registration.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void getWarning(String Head, String body){
        Stage stage = (Stage) id_entrance.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type,"");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText(body);

        alert.getDialogPane().setHeaderText(Head);

        alert.showAndWait();
    }
}
