package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DevolutionController {
    @FXML
    private Label successMessage;
    @FXML
    private ChoiceBox moviesList;
    @FXML
    private ChoiceBox clientsList;
    @FXML
    private TextField lateDays;

    private List<Movie> movies;

    private List<Client> clients;


    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("locationMenu");
    }

    public void initialize() throws SQLException {
        attMovieList();
        attClientList();

        clientsList.setOnAction((event) -> {
            try {
                attMovieList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

    private void attMovieList() throws SQLException {
        int selectedIndexClient = clientsList.getSelectionModel().getSelectedIndex();
        if(selectedIndexClient != -1){
            Client client = clients.get(selectedIndexClient);
            movies = Database.getMoviesFromClientId(client.id);
            moviesList.getItems().clear();

            for(Movie movie : movies){
                moviesList.getItems().add(movie.id+" - "+movie.title);
            }
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

        if (selectedIndexClient != -1 && selectedIndexMovie != -1 && lateDays.getText() != ""){
            Client client = clients.get(selectedIndexClient);
            Movie movie = movies.get(selectedIndexMovie);
            int intLateDays = Integer.parseInt(lateDays.getText());
            Location location = Database.getActualLocationByClientAndMovieId(client.id, movie.id);
            location.setLateDays(intLateDays);
            location.setReturned("yes");
            Database.updateLocation(location);
            movie.increaseQuantity();
            Database.updateMovie(movie);
            successMessage.setText("Devolução realizada com sucesso!");
            clientsList.setValue("");
            moviesList.setValue("");
            lateDays.setText("");
            attMovieList();

        }else{
            successMessage.setText("Preencha todos os dados!");
        }




    }

}
