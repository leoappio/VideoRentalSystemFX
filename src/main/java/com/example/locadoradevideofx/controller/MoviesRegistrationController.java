package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MoviesRegistrationController {

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("main");
    }

    public void onCreateClickButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("movieNew");
    }
    public void onAllMoviesClickButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("allMovies");
    }
    public void onDeleteClickButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("deleteMovie");
    }
}
