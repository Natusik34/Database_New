package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddTableDeliveryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_amount;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private ComboBox<?> id_comboBoxNomenclature;

    @FXML
    private TextField id_price;

    @FXML
    private TextField id_sum;

    @FXML
    void ButtonAdd(ActionEvent event) {

    }

    @FXML
    void initialize() {
     //   id_sum = (double) id_price * id_amount;


    }

}
