package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationNewController {
    @FXML
    private Label successMessage;
    @FXML
    private ChoiceBox moviesList;
    @FXML
    private ChoiceBox clientsList;

    private List<Movie> movies;

    private List<Client> clients;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("locationMenu");
    }

    public void initialize() throws SQLException {
        attMovieList();
        attClientList();


    }

    private void attMovieList() throws SQLException {
        movies = Database.getAllMovies();
        moviesList.getItems().clear();

        for(Movie movie : movies){
            moviesList.getItems().add(movie.id+" - "+movie.title);
        }
    }

    private void attClientList() throws SQLException {
        VideoRentalShop store = new VideoRentalShop();
        clients = store.getClients();
        clientsList.getItems().clear();
        for(Client client : clients){
            clientsList.getItems().add(client.id+" - "+client.name);
        }
    }

    public void onLocationClickButton(ActionEvent e) throws SQLException {
        int selectedIndexClient = clientsList.getSelectionModel().getSelectedIndex();
        int selectedIndexMovie = moviesList.getSelectionModel().getSelectedIndex();
        if (selectedIndexClient != -1 && selectedIndexMovie != -1){
            Client client = clients.get(selectedIndexClient);
            Movie movie = movies.get(selectedIndexMovie);
            Location verifyLocation = Database.getLocationByClientAndMovieId(client.id, movie.id);
            if (verifyLocation == null || verifyLocation.returned.equals("yes")) {
                Location location = new Location(client.id, movie.id, 0, "no");
                Database.insertLocation(location);
                movie.decreaseQuantity();
                Database.updateMovie(movie);
                successMessage.setText("Locação realizada com sucesso");
                clientsList.setValue("");
                moviesList.setValue("");
            } else {
                successMessage.setText("esse cliente já alugou este filme e não devolveu!");
            }

        }




    }

}
