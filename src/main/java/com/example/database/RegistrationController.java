package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_confirmationCode;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_login;

    @FXML
    private TextField id_password;

    @FXML
    private Button id_register;

    @FXML
    private TextField id_repeatPassword;

    @FXML
    private Button id_avtoriz;

    @FXML
    private Button id_sendCode;

    @FXML
    void initialize() {
        id_avtoriz.setOnAction(event -> {
            id_avtoriz.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("authorization.fxml"));

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



    }

}
