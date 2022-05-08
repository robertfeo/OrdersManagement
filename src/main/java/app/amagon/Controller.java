package app.amagon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    double x,y;
    public Label lbAnzahlBestellungen;
    public Button btnCustomerAdd;
    public Button btnCustomerRefresh;
    public TableView customerTable;
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
    public void mainScene(@NotNull ActionEvent event) throws IOException, SQLException {
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

    //  CHANGE TO PRODUCTS SCENE
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

    //  EXIT THE PROGRAM
    public void exitProgram(@NotNull ActionEvent event) throws IOException, SQLException {
        final Node source = (Node)event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        DBUtil.dbDisconnect();
        stage.close();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void addCustomerToDatabase(ActionEvent actionEvent) {
        
    }

    public void deleteCustomerFromDatabase(ActionEvent actionEvent) {
    }

    public void refreshCustomerTable(ActionEvent actionEvent) {
        customerTable.getColumns().add(DBUtil.getCustomerList());
    }

    public void refreshDataMain(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DBUtil.dbConnect();
        lbRegKunden.setText(Integer.toString(DBUtil.getTotalCustomers()));
        DBUtil.dbDisconnect();
    }
}