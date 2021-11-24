package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AllClientsController {
    @FXML
    private ListView clientsList;

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("clientRegistration");
    }

    public void initialize() throws SQLException {
        ObservableList<String> clientsListView = FXCollections.observableArrayList ();
        List<Client> clients = Database.getAllClients();

        for(Client client : clients){
            clientsListView.add(client.name+" - "+client.phone);
        }

        clientsList.setItems(clientsListView);
    }
}
