package com.example.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EditTableSaleController {

    String idSale, sale, nom,idNom;

    List listIdSale, listSale, listNom, listIdNom;

    List<String>id_LISTSale = new ArrayList<>();
    List<String>sale_LIST = new ArrayList<>();
    List<String>nom_LIST = new ArrayList<>();
    List<String>id_LISTNom = new ArrayList<>();

    String GetSale, GetNom, strSale, strNom;

    DataSingleton dataS = DataSingleton.getInstance();
    String idNomenclatureSale;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_amount;

    @FXML
    private Button id_buttonEdit;

    @FXML
    private ComboBox<?> id_comboBoxNomenclature;

    @FXML
    private ComboBox<?> id_comboBoxSale;

    @FXML
    private TextField id_price;

    @FXML
    private TextField id_sum;


    @FXML
    void initialize() {
        ComboBoxTableSale();
        ComboBoxTableNomenclature();

        id_comboBoxNomenclature.setOnAction(e->{

            DBConnection dbConnection = new DBConnection();
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)) {
                String q1= "SELECT price FROM public.nomenklatyra WHERE naimenovanie = ?";
                PreparedStatement pstmt = con.prepareStatement(q1);

                pstmt.setString(1, (String) id_comboBoxNomenclature.getSelectionModel().getSelectedItem());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    id_price.setText(rs.getString("price"));
                }

                System.out.println(pstmt);
                con.close();
                pstmt.close();
            } catch (SQLException ex) {

            }
        });

        id_amount.textProperty().addListener((observable, oldValue, newValue) -> {
            // String str = id_price.getText();
            // System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_price.getText());
            id_sum.setText(String.valueOf(str1));
        });
        id_price.textProperty().addListener((observable, oldValue, newValue) -> {
            //  String str = id_price.getText();
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_amount.getText());
            id_sum.setText(String.valueOf(str1));
        });

        idNomenclatureSale = dataS.getIdSaleNomenclature();
        //id_editName.setText("????????????");
        //System.out.println(dataS.getIdIzerenie());
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from public.nomenklatyra_prodasha where id_nomenklatyra_prodasha = '" + idNomenclatureSale + "';");
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                id_comboBoxSale.getValue();
                id_comboBoxNomenclature.getValue();
                id_amount.setText(row.get(3));
                id_price.setText(row.get(4));
                id_sum.setText(row.get(5));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ButtonEdit(ActionEvent actionEvent) {
        DBConnection dbConnection = new DBConnection();
        GetSale = String.valueOf(id_comboBoxSale.getSelectionModel().getSelectedIndex());
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());
        strSale = String.valueOf(sale_LIST.indexOf(sale_LIST.get(Integer.parseInt(GetSale))));
        strNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
        String idSale = id_LISTSale.get(Integer.parseInt(strSale));
        String idNomenclature = id_LISTNom.get(Integer.parseInt(strNom));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE public.nomenklatyra_prodasha SET  id_prodasha= '"+idSale+"',id_nomenklatyra = '"+idNomenclature+"', kolichestvo_prodasha = '"+id_amount.getText()+"', price_prodasha = '"+id_price.getText()+"', summa_prodasha = '"+id_sum.getText()+"' WHERE id_nomenklatyra_prodasha='"+idNomenclatureSale+"' ;");
        statement.close();
        } catch (SQLException throwables) {// id_editName.getText()   Peremennie.id
            throwables.printStackTrace();
        }
        Stage stage = (Stage) id_buttonEdit.getScene().getWindow();
        stage.close();
    }


    public void ComboBoxTableSale(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.prodasha");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idSale = listRow.get(0);
                sale = listRow.get(1);

                listIdSale = new ArrayList<>(Collections.singleton(idSale));
                listSale = new ArrayList<>(Collections.singleton(sale));

                id_LISTSale.add(idSale);
                sale_LIST.add(sale);

                sale_LIST.indexOf(sale_LIST);

                System.out.println("?????????????????? ?????????????? "+ listSale.get(0));
                id_comboBoxSale.getItems().addAll(listSale);
                id_comboBoxSale.getSelectionModel().select(0);

                GetSale = String.valueOf(id_comboBoxSale.getSelectionModel().getSelectedIndex());

                strSale = String.valueOf(sale_LIST.indexOf(sale_LIST.get(Integer.parseInt(GetSale))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(sale_LIST.get(0));
            System.out.println(id_LISTSale.get(Integer.parseInt(strSale)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboBoxTableNomenclature(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.nomenklatyra");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idNom = listRow.get(0);
                nom = listRow.get(1);

                listIdNom = new ArrayList<>(Collections.singleton(idNom));
                listNom = new ArrayList<>(Collections.singleton(nom));

                id_LISTNom.add(idNom);
                nom_LIST.add(nom);

                nom_LIST.indexOf(nom_LIST);

                System.out.println("?????????????????? ?????????????? "+ listNom.get(0));
                id_comboBoxNomenclature.getItems().addAll(listNom);
                id_comboBoxNomenclature.getSelectionModel().select(0);

                GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

                strNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(nom_LIST.get(0));
            System.out.println(id_LISTNom.get(Integer.parseInt(strNom)));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}