package com.example.locadoradevideofx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onClientButtonClick() throws IOException {
        HelloApplication.changeScreen("clientRegistration");
    }

    @FXML
    protected void onMovieButtonClick() throws IOException {
        HelloApplication.changeScreen("movieRegistration");
    }

    @FXML
    protected void onLocationButtonClick() throws IOException {
        HelloApplication.changeScreen("locationMenu");
    }

    @FXML
    protected void onReportsButtonClick() throws IOException {
        HelloApplication.changeScreen("reportsMenu");
    }

}