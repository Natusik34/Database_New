package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddUnitOfMeasurementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private TextField id_name;

    @FXML
    void initialize() {


    }

    public void addButton(ActionEvent actionEvent) {
        DBConnection.Insert("INSERT INTO public.izmerenie(naimenovanie)" + "VALUES(" + id_name.getText() + "");
        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }

    //DBConnection.Insert("INSERT INTO public.izmerenie(naimenovanie)" + "VALUES(\'" + id_name.getText() + "\'");

}

