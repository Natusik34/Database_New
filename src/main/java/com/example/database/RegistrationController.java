package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Button id_sendCode;

    @FXML
    void initialize() {


    }

}
