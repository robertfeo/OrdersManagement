package com.example.restaurantorders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Label lbStatus;
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnDbDisconnect;
    @FXML
    private Button btnDbConnect;
    @FXML
    private AnchorPane scenePane;

    Stage stage;

    @FXML
    protected void connectToDatabase() throws IOException,NullPointerException {
        lbStatus.setTextFill(Color.color(0, 0.5, 0.2));
        lbStatus.setText("Verbindung erfolgreich hergestellt.");
        btnDbConnect.setDisable(true);
        btnDbDisconnect.setDisable(false);
    }

    @FXML
    protected void disconnectFromDatabase() throws IOException,NullPointerException {
        lbStatus.setTextFill(Color.color(0.7, 0.1, 0.1));
        lbStatus.setText("Verbindung wurde getrennt.");
        btnDbDisconnect.setDisable(true);
        btnDbConnect.setDisable(false);
    }

    @FXML
    protected void quitProg() throws IOException{
        this.stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

}