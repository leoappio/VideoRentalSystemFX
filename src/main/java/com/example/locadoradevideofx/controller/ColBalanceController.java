package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Database;
import com.example.locadoradevideofx.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ColBalanceController {
    @FXML
    private ListView moviesList;

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("reportsMenu");
    }

    public void initialize() throws SQLException {
        ObservableList<String> moviesListView = FXCollections.observableArrayList ();
        List<Movie> movies = Database.getAllMovies();

        for(Movie movie : movies){
            if(movie.type == 1){
                moviesListView.add(movie.title+" - Devolução em 24H - "+movie.quantity+" un.");
            }else{
                moviesListView.add(movie.title+" - Devolução em 48H - "+movie.quantity+" un.");
            }
        }

        moviesList.setItems(moviesListView);
    }
}
