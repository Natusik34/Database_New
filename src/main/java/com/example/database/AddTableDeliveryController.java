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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTableDeliveryController {
    String Count;
    String idDeliv, deliv, nom,idNom;

    List listIdDeliv, listDeliv, listNom, listIdNom;

    List<String>id_LISTDeliv = new ArrayList<>();
    List<String>deliv_LIST = new ArrayList<>();
    List<String>nom_LIST = new ArrayList<>();
    List<String>id_LISTNom = new ArrayList<>();

    String GetDeliv, GetNom, strDeliv, srtNom;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_amount;

    @FXML
    private Button id_buttonAdd;

    @FXML
    private ComboBox<?> id_comboBoxDelivery;

    @FXML
    private ComboBox<?> id_comboBoxNomenclature;

    @FXML
    private TextField id_price;

    @FXML
    private TextField id_sum;

    @FXML
    void ButtonAdd(ActionEvent event) {
        GetDeliv = String.valueOf(id_comboBoxDelivery.getSelectionModel().getSelectedIndex());
        GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

        strDeliv = String.valueOf(deliv_LIST.indexOf(deliv_LIST.get(Integer.parseInt(GetDeliv))));
        srtNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));

        String idDelivery = id_LISTDeliv.get(Integer.parseInt(strDeliv));
        String idNomenclature = id_LISTNom.get(Integer.parseInt(srtNom));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.nomenklatyra_postavka(id_postavka, id_nomenklatyra, kolichestvo_postavka, price_postavka, summa_postavka) VALUES('" + idDelivery + "', '" + idNomenclature + "','" + id_amount.getText() + "','" + id_price.getText() + "', '" + id_sum.getText() + "')");
            getIdNomen();

            int countfinal =  Integer.parseInt(Count) + Integer.parseInt(id_amount.getText());

            int rows1 = statement.executeUpdate("UPDATE public.cklad\n" +
                    "\tSET  kolichestvo_cklad='"+String.valueOf(countfinal)+"'\n" +
                    "\tWHERE id_nomenklatyra='"+id_LISTNom.get(Integer.parseInt(srtNom))+"';");

            statement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
     //   id_sum = (double) id_price * id_amount;
        ComboBoxDelivery();
        ComboBoxNomenclature();
        //id_amount.setText("0");
        //id_price.setText("0");
        id_amount.textProperty().addListener((observable, oldValue, newValue) -> {
            // String str = id_price.getText();
            // System.out.println("textfield changed from " + oldValue + " to " + newValue);
            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_price.getText());
            id_sum.setText(String.valueOf(str1));
        });
        id_price.textProperty().addListener((observable, oldValue, newValue) -> {

            double str1 = Double.parseDouble(newValue) * Double.parseDouble(id_amount.getText());
            id_sum.setText(String.valueOf(str1));
        });


    }

    public void ComboBoxDelivery(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.postavka");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idDeliv = listRow.get(0);
                deliv = listRow.get(1);
                //post = listRow.get(2);

                listIdDeliv = new ArrayList<>(Collections.singleton(idDeliv));
                listDeliv = new ArrayList<>(Collections.singleton(deliv));
                //listPost = new ArrayList<>(Collections.singleton(post));

                id_LISTDeliv.add(idDeliv);
                deliv_LIST.add(deliv);
                //post_LIST.add(post);

                deliv_LIST.indexOf(deliv_LIST);
                //post_LIST.indexOf(post_LIST);

                System.out.println("ВЫбранный элемент "+ listDeliv.get(0));
                //System.out.println("ВЫбранный элемент "+ listPost.get(0));

                id_comboBoxDelivery.getItems().addAll(listDeliv);
                //id_supplier.getItems().addAll(listPost);

                id_comboBoxDelivery.getSelectionModel().select(0);
                //id_supplier.getSelectionModel().select(0);

                GetDeliv = String.valueOf(id_comboBoxDelivery.getSelectionModel().getSelectedIndex());
                //GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

                strDeliv = String.valueOf(deliv_LIST.indexOf(deliv_LIST.get(Integer.parseInt(GetDeliv))));
                //srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));
            }
            con.close();
            statement.close();
            rs.close();
            System.out.println(deliv_LIST.get(0));
            System.out.println(id_LISTDeliv.get(Integer.parseInt(strDeliv)));
           /* System.out.println(post_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(srtPost)));*/

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboBoxNomenclature(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.nomenklatyra");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idNom = listRow.get(0);
                //cklad = listRow.get(1);
                nom = listRow.get(1);

                listIdNom = new ArrayList<>(Collections.singleton(idNom));
                //listCklad = new ArrayList<>(Collections.singleton(cklad));
                listNom = new ArrayList<>(Collections.singleton(nom));

                id_LISTNom.add(idNom);
                //cklad_LIST.add(cklad);
                nom_LIST.add(nom);

                //cklad_LIST.indexOf(cklad_LIST);
                nom_LIST.indexOf(nom_LIST);

                //System.out.println("ВЫбранный элемент "+ listCklad.get(0));
                System.out.println("ВЫбранный элемент "+ listNom.get(0));

                //id_warehouse.getItems().addAll(listCklad);
                id_comboBoxNomenclature.getItems().addAll(listNom);

                //id_warehouse.getSelectionModel().select(0);
                id_comboBoxNomenclature.getSelectionModel().select(0);

                //GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());
                GetNom = String.valueOf(id_comboBoxNomenclature.getSelectionModel().getSelectedIndex());

                //strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
                srtNom = String.valueOf(nom_LIST.indexOf(nom_LIST.get(Integer.parseInt(GetNom))));
            }
            con.close();
            statement.close();
            rs.close();
            /*System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));*/
            System.out.println(nom_LIST.get(0));
            System.out.println(id_LISTNom.get(Integer.parseInt(srtNom)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getIdNomen(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT kolichestvo_cklad\n" +
                    "\tFROM public.cklad\n" +
                    "\twhere \"id_nomenklatyra\"= '"+id_LISTNom.get(Integer.parseInt(srtNom))+"';");
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

    }}
