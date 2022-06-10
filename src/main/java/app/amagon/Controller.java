package app.amagon;

import app.amagon.entities.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.amagon.entities.Customer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;

public class Controller{
    public TableColumn<Customer,Integer> tbCustomerID;
    public TableColumn<Customer,String>  tbCustomerSurname;
    public TableColumn<Customer,String> tbCustomerName;
    public TableColumn<Customer,String> tbCustomerAddress;
    public TableColumn<Customer,String> tbCustomerCity;
    public TextField txfDeleteByID;
    public TextField txfSurname;
    public TextField txfAddress;
    public TextField txfName;
    public TextField txfCity;
    public ChoiceBox<Integer> cbCustomerID;
    public TextField txfSearchCustomerByName;
    public TableView<Product> productTable;
    public TableColumn<Product,Integer> tbProductID;
    public TableColumn<Product,String> tbProductName;
    public TableColumn<Product,String> tbProductCategory;
    public TableColumn<Product, BigDecimal> tbProductPrice;
    double x,y;
    public Label lbAnzahlBestellungen;
    public Button btnCustomerAdd;
    public Button btnCustomerRefresh;
    public TableView<Customer> customerTable;
    @FXML
    private Label lbRegKunden;
    private Stage stage;
    private Scene scene;

    //  CHANGE TO MAIN SCENE
    public void mainScene(@NotNull ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/main.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    //  CHANGE TO ORDER SCENE
    public void bestellungScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/bestellungen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    //  CHANGE TO CUSTOMERS SCENE
    public void kundenScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/kunden.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    public void productsScene(@NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/produkte.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getRoot().setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        scene.getRoot().setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX() - x);
            stage.setY(evt.getScreenY() - y);
        });
        stage.show();
    }

    public void exitProgram(@NotNull ActionEvent event) throws SQLException {
        final Node source = (Node)event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        DBUtil.dbDisconnect();
        stage.close();
    }

    public void addCustomerToDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.addCustomer(txfSurname.getText(),txfName.getText(),txfAddress.getText(),txfCity.getText());
            this.refreshCustomerTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void deleteCustomerFromDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        try{
            DBUtil.deleteCustomerByID(txfDeleteByID.getText());
            this.refreshCustomerTable();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        DBUtil.dbDisconnect();
    }

    public void refreshCustomerTable() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        tbCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbCustomerSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tbCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        customerTable.setItems(DBUtil.getCustomerList());
        DBUtil.dbDisconnect();
        refreshCustomerChoiceList();
    }

    public void refreshProductTable() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        tbProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tbProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tbProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tbProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(DBUtil.getProductList());
        DBUtil.dbDisconnect();
    }

    public void refreshCustomerChoiceList() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        cbCustomerID.setItems(DBUtil.getCustomerIDs());
        DBUtil.dbDisconnect();
    }

    public void searchCustomer() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        if (!cbCustomerID.getSelectionModel().isEmpty()){
            DBUtil.getCustomerByID(String.valueOf(cbCustomerID.getSelectionModel().getSelectedItem()));
            for (Customer c : DBUtil.getCustomerList()){
                if (c.getCustomerId() == cbCustomerID.getSelectionModel().getSelectedItem()){
                    txfSearchCustomerByName.setText(c.getName());
                }
            }
        }
        DBUtil.dbDisconnect();
    }

    public void refreshDataMain() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        lbRegKunden.setText(Integer.toString(DBUtil.getTotalCustomers()));
        DBUtil.dbDisconnect();
    }

    public void clickRow(MouseEvent mouseEvent) {
        try{
            txfSurname.setText(customerTable.getSelectionModel().getSelectedItem().getSurname());
            txfName.setText(customerTable.getSelectionModel().getSelectedItem().getName());
            txfAddress.setText(customerTable.getSelectionModel().getSelectedItem().getAddress());
            txfCity.setText(customerTable.getSelectionModel().getSelectedItem().getCity());
        }catch(NullPointerException ex){
            return;
        }
    }
}