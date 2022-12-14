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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SalePageController {

    Stage window;

    DataSingleton dataS = DataSingleton.getInstance();

    ObservableList<PieChart.Data> DATA = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button id_buttonDelete;

    @FXML
    private Button id_buttonDeleteRow;

    @FXML
    private Button id_buttonInput;

    @FXML
    private Button id_buttonInputRow;

    @FXML
    private Button id_buttonOutput;

    @FXML
    private Button id_buttonOutputRow;

    @FXML
    private Button id_delivery;

    @FXML
    private Button id_nomenclature;

    @FXML
    private TextField id_search;

    @FXML
    private Button id_supplier;

    @FXML
    private TableView<ObservableList> id_tableSale;

    @FXML
    private TableView<ObservableList> id_tableSaleNomenclature;

    @FXML
    private Button id_unitOfMeasurement;

    @FXML
    private Button id_warehouse;

    @FXML
    private PieChart id_salesChart;

    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataTable;

    @FXML
    void initialize() {
        UpdateSale();
        UpdateSaleNomenclature();
        Diagram();
    }

    @FXML
    protected void buttonNomenclature() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nomenclaturePage.fxml"));
        window = (Stage) id_nomenclature.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSupplier() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supplierPage.fxml"));
        window = (Stage) id_supplier.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSupply() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("deliveryPage.fxml"));
        window = (Stage) id_delivery.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonWarehouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("warehousePage.fxml"));
        window = (Stage) id_warehouse.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonUnitOfMeasurement() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("unitOfMeasurementPage.fxml"));
        window = (Stage) id_unitOfMeasurement.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void showAdd(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEdit(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDelete(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcell(MouseEvent mouseEvent) {
        ObservableList sale = id_tableSale.getSelectionModel().getSelectedItem();
        //  System.out.println(izm.get(0).toString());
        dataS.setIdSale(sale.get(0).toString());
        //System.out.println(dataS.getIdIzerenie());

        Peremennie.idSale = Integer.parseInt(sale.get(0).toString());
        Peremennie.nomNakSale = sale.get(1).toString();
        Peremennie.DataSale = sale.get(2).toString();
        Peremennie.idCkladSale = Integer.parseInt(sale.get(3).toString());
    }

    public void getcellTable(MouseEvent mouseEvent) {
        ObservableList saleTable = id_tableSaleNomenclature.getSelectionModel().getSelectedItem();
        //  System.out.println(izm.get(0).toString());
        dataS.setIdSaleNomenclature(saleTable.get(0).toString());
        //System.out.println(dataS.getIdIzerenie());

        Peremennie.idNomenclatureSaleTable = Integer.parseInt(saleTable.get(0).toString());
        Peremennie.idSaleTable = Integer.parseInt(saleTable.get(1).toString());
        Peremennie.idNomSale = Integer.parseInt(saleTable.get(2).toString());
        Peremennie.amountSale = saleTable.get(3).toString();
        Peremennie.priceSale = saleTable.get(4).toString();
        Peremennie.sumSale = saleTable.get(5).toString();
    }

    public void showAddRow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addTableSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditRow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editTableSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteRow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteTableSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление строки");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReset(ActionEvent actionEvent) {
        id_tableSale.getColumns().clear();
        UpdateSale();
    }

    public void showResetRow(ActionEvent actionEvent) {
        id_tableSaleNomenclature.getColumns().clear();
        UpdateSaleNomenclature();
    }

    public void UpdateSale() {
        data = FXCollections.observableArrayList();
        try {
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from prodasha");
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableSale.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            id_tableSale.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void UpdateSaleNomenclature() {
        dataTable = FXCollections.observableArrayList();
        try {
            DBConnection con = new DBConnection();
            con.Connection();
            ResultSet rs = con.gettable("Select * from nomenklatyra_prodasha");
            for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {


                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                id_tableSaleNomenclature.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                dataTable.add(row);
            }
            id_tableSaleNomenclature.setItems(dataTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Diagram() {
        String nomenclature;
        String amount;
        ObservableList<PieChart.Data> pieChart = null;
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/vasiltsova_awtozaprawka", "Vasiltsova", "Vasiltsova")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT naimenovanie, sum(kolichestvo_prodasha) FROM public.nomenklatyra_prodasha\n" +
                    "JOIN public.nomenklatyra ON public.nomenklatyra_prodasha.id_nomenklatyra = public.nomenklatyra.id_nomenklatyra\n" +
                    "GROUP BY naimenovanie, kolichestvo_prodasha;");
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                nomenclature = row.get(0);
                amount = row.get(1);
                DATA.add(new PieChart.Data(nomenclature, Integer.parseInt(amount)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        id_salesChart.setData(DATA);
    }
}

