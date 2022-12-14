package com.example.database;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.Double.parseDouble;

public class AddTableSaleController {
    String Count;
    String idSale, sale, nom,idNom;

    List listIdSale, listSale, listNom, listIdNom;

    List<String>id_LISTSale = new ArrayList<>();
    List<String>sale_LIST = new ArrayList<>();
    List<String>nom_LIST = new ArrayList<>();
    List<String>id_LISTNom = new ArrayList<>();

    String GetSale, GetNom, strSale, strNom;

    @FXML
    private AnchorPane AnchorP;

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
    private ComboBox<?> id_comboBoxSale;

    @FXML
    private TextField id_price;

    @FXML
    private TextField id_sum;
    int countfinal = 0;
    @FXML
    void ButtonAdd(ActionEvent event) {
        DBConnection dbConnection = new DBConnection();
        GetSale = String.valueOf(id_comboBoxSale.getSelectionModel().getSelectedIndex());
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

        strSale = String.valueOf(sale_LIST.indexOf(sale_LIST.get(Integer.parseInt(GetSale))));
        strNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));

        String idSalE = id_LISTSale.get(Integer.parseInt(strSale));
        String idNomen = id_LISTNom.get(Integer.parseInt(strNom));
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();

            getIdNomen();
            if(Integer.parseInt(Count)>Integer.parseInt(id_amount.getText())){
            int rows = statement.executeUpdate("INSERT INTO public.nomenklatyra_prodasha(id_prodasha, id_nomenklatyra, kolichestvo_prodasha, price_prodasha, summa_prodasha) VALUES('" + idSalE + "','" + idNomen + "','" + id_amount.getText() + "','" + id_price.getText() + "', '" + id_sum.getText() + "')");
            countfinal = Integer.parseInt(Count) - Integer.parseInt(id_amount.getText());
            int rows1 = statement.executeUpdate("UPDATE public.cklad\n" +
                    "\tSET  kolichestvo_cklad='"+String.valueOf(countfinal)+"'\n" +
                    "\tWHERE id_nomenklatyra='"+id_LISTNom.get(Integer.parseInt(strNom))+"';");
        } else{ getWarning("?????????????????????? ????????????", "???????????????????? ?????????????? ???? ????????????");}
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
        //tableView.refresh();

    }

    @FXML
    void initialize() {

        ComboBoxTableSale();
        ComboBoxTableNomenclature();
        //id_amount.setText("0");
        //id_price.setText("0");

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
    public void getIdNomen(){
        DBConnection dbConnection = new DBConnection();
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", dbConnection.username, dbConnection.password)){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT kolichestvo_cklad\n" +
                    "\tFROM public.cklad\n" +
                    "\twhere \"id_nomenklatyra\"= '"+id_LISTNom.get(Integer.parseInt(strNom))+"';");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                Count = listRow.get(0);

            }
            con.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void getWarning(String Head, String body){
        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type,"");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText(body);

        alert.getDialogPane().setHeaderText(Head);

        alert.showAndWait();
    }

}