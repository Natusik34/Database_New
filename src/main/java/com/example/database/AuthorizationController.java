package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_entrance;

    @FXML
    private TextField id_login;

    @FXML
    private TextField id_password;

    @FXML
    private Button id_registration;

    @FXML
    void initialize() {
        id_registration.setOnAction(event -> {

        });

    }

}
