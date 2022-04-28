package com.example.restaurantorders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void connectToDatabase() throws IOException {
        System.out.println("connect to database");
    }

    @FXML
    protected void disconnectFromDatabase() throws IOException {
        System.out.println("disconnect from database");
    }

}