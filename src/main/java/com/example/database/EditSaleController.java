package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditSaleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private DatePicker id_saleDate;

    @FXML
    private ComboBox<?> id_warehouse;

    @FXML
    void initialize() {


    }

    public void ButtonEdit(ActionEvent actionEvent) {
    }
}


