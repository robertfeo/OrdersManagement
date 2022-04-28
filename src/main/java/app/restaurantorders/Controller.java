package app.restaurantorders;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.restaurantorders.DBUtil;

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

    private DBUtil dbUtils;

    @FXML
    protected void connectToDatabase() throws SQLException, ClassNotFoundException {
        dbUtils.dbConnect();
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

    @FXML
    protected void quitProg() throws IOException{
        System.out.println("Quit pressed");
        this.stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

}