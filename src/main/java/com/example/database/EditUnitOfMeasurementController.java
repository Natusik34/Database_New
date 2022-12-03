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
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditUnitOfMeasurementController {

    DataSingleton dataS = DataSingleton.getInstance();
    String idIzmerenie;
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
        idIzmerenie = dataS.getIdIzerenie();
        id_editName.setText("Привет");
        System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.izmerenie where id_izmerenie = '" + idIzmerenie + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_editName.setText(row.get(1));
                }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void buttonEditUnitOfMeasurement(ActionEvent actionEvent) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
         /*   idIzmerenie = dataS.getIdIzerenie();


            ResultSet rs =  statement.executeUpdate("Select * from public.izmerenie where id_izmerenie = '" + idIzmerenie + "';");

            ObservableList<String> row = FXCollections.observableArrayList();
            for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                row.add(rs.getString(i));
            }
            id_editName.setText(row.get(0));
            System.out.println(row.get(0));
            System.out.println(row.get(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


            int rows = statement.executeUpdate("UPDATE public.izmerenie\n" +
                    "\tSET  naimenovanie='"+id_editName.getText()+"'" +"\n" +
                    "\tWHERE id_izmerenie='"+idIzmerenie+"' ;");
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();

    }
}
