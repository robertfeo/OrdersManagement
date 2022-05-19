package app.amagon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.amagon.entities.Customer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

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
    DBUtil db_utils = new DBUtil();
    double x,y;
    public Label lbAnzahlBestellungen;
    public Button btnCustomerAdd;
    public Button btnCustomerRefresh;
    public TableView<Customer> customerTable;
    @FXML
    private Label lbStatus;
    @FXML
    private Label lbRegKunden;
    @FXML
    private Button btnDbDisconnect;
    @FXML
    private Button btnDbConnect;
    @FXML
    private AnchorPane rootPane;
    private Stage stage;
    private Scene scene;

    @FXML
    protected void connectToDatabase() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        lbStatus.setTextFill(Color.color(0, 0.5, 0.2));
        lbStatus.setText("Verbindung erfolgreich hergestellt.");
        btnDbConnect.setDisable(true);
        btnDbDisconnect.setDisable(false);
    }

    @FXML   //  USED IN BUTTON TO CONNECT TO DATABASE
    protected void disconnectFromDatabase() throws SQLException {
        DBUtil.dbDisconnect();
        lbStatus.setTextFill(Color.color(0.7, 0.1, 0.1));
        lbStatus.setText("Verbindung wurde getrennt.");
        btnDbDisconnect.setDisable(true);
        btnDbConnect.setDisable(false);
    }

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
    public void kundenScene(@NotNull ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
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
        customerTable.setItems(db_utils.getCustomerList());
        DBUtil.dbDisconnect();
    }

    public void refreshDataMain() throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        lbRegKunden.setText(Integer.toString(DBUtil.getTotalCustomers()));
        DBUtil.dbDisconnect();
    }
}