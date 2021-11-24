package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import com.example.locadoradevideofx.model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteMovieController {
    @FXML
    private Label successMessage;
    @FXML
    private ChoiceBox moviesList;

    private List<Movie> movies;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("movieRegistration");
    }

    public void initialize() throws SQLException {
        attMovieList();

    }

    private void attMovieList() throws SQLException {
        movies = Database.getAllMovies();

        moviesList.getItems().clear();
        for(Movie movie : movies){
            moviesList.getItems().add(movie.id+" - "+movie.title);
        }
    }

    public void onDeleteButtonClick(ActionEvent e) throws SQLException {
        if(moviesList.getSelectionModel().getSelectedItem() != null){
            int selectedIndex = moviesList.getSelectionModel().getSelectedIndex();
            Database.deleteMovie(movies.get(selectedIndex).id);
            successMessage.setText("Filme apagado com sucesso!");
            attMovieList();
        }else{
            successMessage.setText("Selecione um filme para apagar!");
        }

    }

}
