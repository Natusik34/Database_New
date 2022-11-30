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

public class EditUnitOfMeasurementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private TextField id_editName;

    @FXML
    void initialize() {

    }

    public void buttonEditUnitOfMeasurement(ActionEvent actionEvent) {

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("UPDATE public.izmerenie\n" +
                    "\tSET  naimenovanie='"+id_editName.getText()+"'" +"\n" +
                    "\tWHERE id_izmerenie='"+Peremennie.id+"' ;");
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }

    }
}
