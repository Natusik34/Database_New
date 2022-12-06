package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddDeliveryController {

    Stage window;

    String id, cklad, post,idPost;

    List listId, listCklad, listPost, listIdPost;

    List<String>id_LIST = new ArrayList<>();
    List<String>cklad_LIST = new ArrayList<>();
    List<String>post_LIST = new ArrayList<>();
    List<String>id_LISTPost = new ArrayList<>();

    String GetCklad, GetPost, strCkl, srtPost;

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
    private TextField id_deliveryDate;

    @FXML
    private TextField id_invoiceNumber;

    @FXML
    private ComboBox<?> id_supplier;

    @FXML
    private TableView id_tableDeliveryNomenclature;

    @FXML
    private ComboBox<?> id_warehouse;

    private ObservableList<ObservableList> data;

    @FXML
    protected void initialize() {
        data = FXCollections.observableArrayList();
        try{
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from nomenklatyra_postavka");
            for(int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableDeliveryNomenclature.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableDeliveryNomenclature.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ComboBoxDeliveryWarehouse();
        ComboBoxDeliverySupplier();

    }
//склад,поставщик
    public void ComboBoxDeliveryWarehouse(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.cklad");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                id = listRow.get(0);
                cklad = listRow.get(1);
                //post = listRow.get(2);

                listId = new ArrayList<>(Collections.singleton(id));
                listCklad = new ArrayList<>(Collections.singleton(cklad));
                //listPost = new ArrayList<>(Collections.singleton(post));

                id_LIST.add(id);
                cklad_LIST.add(cklad);
                //post_LIST.add(post);

                cklad_LIST.indexOf(cklad_LIST);
                //post_LIST.indexOf(post_LIST);

                System.out.println("ВЫбранный элемент "+ listCklad.get(0));
                //System.out.println("ВЫбранный элемент "+ listPost.get(0));

                id_warehouse.getItems().addAll(listCklad);
                //id_supplier.getItems().addAll(listPost);

                id_warehouse.getSelectionModel().select(0);
                //id_supplier.getSelectionModel().select(0);

                GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());
                //GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

                strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
                //srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));
            }
            System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));
           /* System.out.println(post_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(srtPost)));*/

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboBoxDeliverySupplier(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * from public.postavchik");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                idPost = listRow.get(0);
                //cklad = listRow.get(1);
                post = listRow.get(1);

                listIdPost = new ArrayList<>(Collections.singleton(idPost));
                //listCklad = new ArrayList<>(Collections.singleton(cklad));
                listPost = new ArrayList<>(Collections.singleton(post));

                id_LISTPost.add(idPost);
                //cklad_LIST.add(cklad);
                post_LIST.add(post);

                //cklad_LIST.indexOf(cklad_LIST);
                post_LIST.indexOf(post_LIST);

                //System.out.println("ВЫбранный элемент "+ listCklad.get(0));
                System.out.println("ВЫбранный элемент "+ listPost.get(0));

                //id_warehouse.getItems().addAll(listCklad);
                id_supplier.getItems().addAll(listPost);

                //id_warehouse.getSelectionModel().select(0);
                id_supplier.getSelectionModel().select(0);

                //GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());
                GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

                //strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
                srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));
            }
            /*System.out.println(cklad_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(strCkl)));*/
            System.out.println(post_LIST.get(0));
            System.out.println(id_LIST.get(Integer.parseInt(srtPost)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void buttonAdd(ActionEvent actionEvent) throws IOException {
        /*try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addTableDelivery.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Stage stage = new Stage();
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("addTableDelivery.fxml"));
        stage.setTitle("Добавление новой строки");
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    public void buttonEdit(ActionEvent actionEvent) {
    }

    public void buttonDelete(ActionEvent actionEvent) {
    }

    public void AddDelivery(ActionEvent actionEvent) {
        GetCklad = String.valueOf(id_warehouse.getSelectionModel().getSelectedIndex());
        GetPost = String.valueOf(id_supplier.getSelectionModel().getSelectedIndex());

        strCkl = String.valueOf(cklad_LIST.indexOf(cklad_LIST.get(Integer.parseInt(GetCklad))));
        srtPost = String.valueOf(post_LIST.indexOf(post_LIST.get(Integer.parseInt(GetPost))));

        String idCklad = id_LIST.get(Integer.parseInt(strCkl));
        String idPost = id_LISTPost.get(Integer.parseInt(srtPost));

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")){
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("INSERT INTO public.postavka(nomer_nakladnoi, data_postavki, id_cklad, id_postavchik) VALUES('" + id_invoiceNumber.getText() + "','" + id_deliveryDate.getText() + "','" + idCklad + "', '" + idPost + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Stage stage = (Stage) id_buttonAdd.getScene().getWindow();
        stage.close();
    }
}

