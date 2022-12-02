package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class EditUnitOfMeasurementController {
DataSingleton data = DataSingleton.getInstance();
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

            String idIzmerenie = data.getIdIzerenie();


            DBConnect dbConnect = new DBConnect();
            dbConnect.username = "PetrovR";
            dbConnect.password = "PetrovR";
            try {
                dbConnect.ConnectToDB();
                String SQL = "SELECT\n" +
                        "  DISTINCT \n" +
                        "  (\"To\") \"From\",\n" +
                        "  \"To\"\n" +
                        "FROM\n" +
                        "  tickets\n" +
                        "ORDER BY\n" +
                        "  \"From\",\n" +
                        "  \"To\";";/*
  select \"From\", \"To\", count(*)\n" +
                    "from public.tickets\n" +
                    "group by \"From\", \"To\"\n" +
                    "HAVING count(*) >= 1*/
                ResultSet rsF = dbConnect.connection.createStatement().executeQuery(SQL);
                while (rsF.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rsF.getMetaData().getColumnCount(); i++) {
                        row.add(rsF.getString(i));
                    }
                    Fr = row.get(0);
                    To = row.get(1);
                    List listFr = new ArrayList<>(Collections.singleton(Fr));
                    List listTo = new ArrayList<>(Collections.singleton(To));
                    fromComboBox.getItems().addAll(listFr);
                    toComboBox.getItems().addAll(listTo);

                    fromComboBox.getSelectionModel().select(0);
                    toComboBox.getSelectionModel().select(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }

            int rows = statement.executeUpdate("UPDATE public.izmerenie\n" +
                    "\tSET  naimenovanie='"+id_editName.getText()+"'" +"\n" +
                    "\tWHERE id_izmerenie='"+Peremennie.id+"' ;");
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }

    }
}
