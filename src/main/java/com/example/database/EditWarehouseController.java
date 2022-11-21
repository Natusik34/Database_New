package com.example.database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EditWarehouseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonOutput;

    @FXML
    private TableColumn<?, ?> id_columnNomenclature;

    @FXML
    private TableColumn<?, ?> id_columnQuantity;

    @FXML
    private TextField id_name;

    @FXML
    private TableView<?> id_tableOfProductsInStock;

    @FXML
    void initialize() {


    }

}


