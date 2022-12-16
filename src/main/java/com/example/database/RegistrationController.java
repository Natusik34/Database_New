package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class RegistrationController {

    private final int min = 0;
    private final int max = 999999;
    private Random random = new Random();
    private String Code;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_confirmationCode;

    @FXML
    private TextField id_email;

    @FXML
    private PasswordField id_password;

    @FXML
    private Button id_register;

    @FXML
    private PasswordField id_repeatPassword;

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

    public void ButtonRegistration(ActionEvent actionEvent) {
        AddUser();
    }

    public void ButtonSendCode(ActionEvent actionEvent) {
        GetCodeEmail();
    }

    public void GetCodeEmail(){
        final String fromEmail = "vasiltsova-natulya@yandex.ru"; //requires valid gmail id
        final String password = "kptvgwqnzkiojzth"; // correct password for gmail id
        final String toEmail = id_email.getText().trim(); // can be any email id

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Code = String.valueOf(random.nextInt(max - min) + min);
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        EmailUtil.sendEmail(session, toEmail,"Вам пришел код! ", Code);

    }

    private void AddUser(){
        DBConnection dbConnection = new DBConnection();
        String email = id_email.getText().trim();
        String code = id_confirmationCode.getText().trim();
        String password = id_password.getText().trim();
        String repeatPassword = id_repeatPassword.getText().trim();

        if(Objects.equals(Code, code) && password.equals(repeatPassword) && !password.equals("") && !repeatPassword.equals("")){
            try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate("Insert into public.users (mail, password) values ('"+email+"', '"+password+"')");
                int rows1 = statement.executeUpdate(
                        "CREATE ROLE \""+email+"\" WITH\n" +
                        "  LOGIN\n" +
                        "  NOSUPERUSER\n" +
                        "  INHERIT\n" +
                        "  NOCREATEDB\n" +
                        "  NOCREATEROLE\n" +
                        "  NOREPLICATION\n" +
                        "  PASSWORD '" + password + "';\n" +
                        "\n" +
                        "GRANT \"GruppaVasiltsova\" TO \""+email+"\";");

                Code = "";
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
