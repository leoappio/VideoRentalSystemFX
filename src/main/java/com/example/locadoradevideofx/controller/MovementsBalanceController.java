package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import com.example.locadoradevideofx.model.Movie;
import com.example.locadoradevideofx.model.VideoRentalShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovementsBalanceController {
    @FXML
    private ListView moviesList;
    @FXML
    private ListView clientsList;
    @FXML
    private Label totalClients;
    @FXML
    private Label totalMovies;
    @FXML
    private Label totalLocations;
    @FXML
    private Label movies24;
    @FXML
    private Label movies48;
    @FXML
    private Label averageLocations;
    @FXML
    private Label averageLate;

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("reportsMenu");
    }

    public void initialize() throws SQLException {
        VideoRentalShop shop = new VideoRentalShop();
        int _totalClients = shop.getTotalClients();
        int _totalMovies = shop.getTotalMovies();
        int _totalLocations = shop.getTotalLocations();
        int _total24hMovies = shop.getTotal24HoursMovies();
        int _total48hMovies = shop.getTotal48HoursMovies();
        float _averageLocations = (float)_totalLocations / _totalClients;
        float _averageLateDays = shop.getAverageLate();
        String strAvgLocations = String.format("%.2f",_averageLocations);
        String strAvgLate = String.format("%.2f",_averageLateDays);

        totalClients.setText("Total de clientes: "+_totalClients);
        totalMovies.setText("Total de filmes: "+_totalMovies);
        totalLocations.setText("Total de locações no mês: "+_totalMovies);
        movies24.setText("Filmes 24H: "+_total24hMovies);
        movies48.setText("Filmes 48H: "+_total48hMovies);
        averageLocations.setText("Média de locações p/ usuário: "+strAvgLocations);
        averageLate.setText("Média de dias de atraso: "+strAvgLate);


        ObservableList<String> moviesListView = FXCollections.observableArrayList ();
        List<Movie> top10Movies = shop.getTop10Movies();

        for(int i = 0; i<top10Movies.size();i++){
            int movieTotalLocations = shop.getTotalLocationsByMovieId(top10Movies.get(i).id);
            moviesListView.add((i+1)+" - "+top10Movies.get(i).title+" - Nº de locações: "+movieTotalLocations);
        }

        moviesList.setItems(moviesListView);

        ObservableList<String> clientsListView = FXCollections.observableArrayList ();
        ArrayList<Client> clients = shop.getTop10Clients();
        for(int i = 0; i<clients.size();i++){
            int clientTotalLocations = shop.getTotalLocationsByClientId(clients.get(i).id);
            clientsListView.add((i+1)+" - "+clients.get(i).name+" - Nº de locações: "+clientTotalLocations);
        }
        clientsList.setItems(clientsListView);
    }
}
