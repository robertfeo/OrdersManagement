package com.example.restaurantorders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    protected void connectToDatabase() throws IOException,NullPointerException {
        lbStatus.setTextFill(Color.color(0, 0.5, 0.2));
        lbStatus.setText("Erfolgreich verbunden mit der Datenbank.");
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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

}