package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSupplierController {

    DataSingleton dataS = DataSingleton.getInstance();
    String idSupplier;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_INN;

    @FXML
    private TextField id_KPP;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private TextField id_name;

    @FXML
    private TextField id_phoneNomber;

    @FXML
    void ButtonEdit(ActionEvent event) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.postavchik SET  naimenovanie='"+id_name.getText()+"', nomer_telefona= '"+id_phoneNomber.getText()+"',\"INN\"= '"+id_INN.getText()+"',\"KPP\"= '"+id_KPP.getText()+"' WHERE id_postavchik='"+idSupplier+"' ;");
        statement.close();
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        idSupplier = dataS.getIdSupplier();
        //id_editName.setText("Привет");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.postavchik where id_postavchik = '" + idSupplier + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_name.setText(row.get(1));
                id_phoneNomber.setText(row.get(2));
                id_INN.setText(row.get(3));
                id_KPP.setText(row.get(4));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


