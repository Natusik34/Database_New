package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditDeliveryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonOutput;

    @FXML
    private DatePicker id_deliveryDate;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private ComboBox<?> id_supplier;

    @FXML
    private ComboBox<?> id_warehouse;

    @FXML
    void initialize() {


    }

}


