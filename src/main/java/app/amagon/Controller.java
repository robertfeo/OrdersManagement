package app.amagon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Controller {
    @FXML
    private Label lbStatus;
    @FXML
    private Button btnDbDisconnect;
    @FXML
    private Button btnDbConnect;
    @FXML
    private AnchorPane scenePane;
    private Stage stage;
    private Scene scene;
    private DBUtil dbUtils;

    @FXML
    protected void connectToDatabase() throws SQLException, ClassNotFoundException {
        this.dbUtils = new DBUtil();
        this.dbUtils.dbConnect();
        lbStatus.setTextFill(Color.color(0, 0.5, 0.2));
        lbStatus.setText("Verbindung erfolgreich hergestellt.");
        btnDbConnect.setDisable(true);
        btnDbDisconnect.setDisable(false);
    }

    @FXML
    protected void disconnectFromDatabase() throws SQLException {
        dbUtils.dbDisconnect();
        lbStatus.setTextFill(Color.color(0.7, 0.1, 0.1));
        lbStatus.setText("Verbindung wurde getrennt.");
        btnDbDisconnect.setDisable(true);
        btnDbConnect.setDisable(false);
    }

    public void mainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/main-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void bestellungScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/bestellungen.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void kundenScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/kunden.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void menuesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/amagon/settings.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram(ActionEvent event) throws IOException {
        final Node source = (Node)event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void check(ActionEvent event) throws IOException {
        final Node source = (Node)event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}