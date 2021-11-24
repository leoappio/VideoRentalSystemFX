package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
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
    private ChoiceBox clientsList;

    private List<Client> clients;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("clientRegistration");
    }

    public void initialize() throws SQLException {
        attClientList();

    }

    private void attClientList() throws SQLException {
        clients = Database.getAllClients();

        clientsList.getItems().clear();
        for(Client client : clients){
            clientsList.getItems().add(client.id+" - "+client.name);
        }
    }

    public void onDeleteButtonClick(ActionEvent e) throws SQLException {
        if(clientsList.getSelectionModel().getSelectedItem() != null){
            int selectedIndex = clientsList.getSelectionModel().getSelectedIndex();
            Database.deleteClient(clients.get(selectedIndex).id);
            successMessage.setText("Cliente apagado com sucesso!");
            attClientList();
        }else{
            successMessage.setText("Selecione um cliente para apagar!");
        }

    }

}
