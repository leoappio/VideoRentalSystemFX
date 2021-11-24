package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditClientController {
    @FXML
    private Label successMessage;
    @FXML
    private ChoiceBox clientsList;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;

    private List<Client> clients;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("clientRegistration");
    }

    public void initialize() throws SQLException {
        attClientList();

        clientsList.setOnAction((event) -> {
            int selectedIndex = clientsList.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1) {
                nameField.setText(clients.get(selectedIndex).name);
                phoneField.setText(clients.get(selectedIndex).phone);
            }
        });
    }

    private void attClientList() throws SQLException {
        clients = Database.getAllClients();
        clientsList.getItems().clear();

        for(Client client : clients){
            clientsList.getItems().add(client.id+" - "+client.name);
        }
    }

    public void onEditClickButton(ActionEvent e) throws SQLException {
        if(nameField.getText()!="" && phoneField.getText()!= ""){
            int selectedIndex = clientsList.getSelectionModel().getSelectedIndex();
            Client clientToEdit = clients.get(selectedIndex);
            clientToEdit.setName(nameField.getText());
            clientToEdit.setPhone(phoneField.getText());
            Database.updateClient(clientToEdit);
            successMessage.setText("Cliente editado com sucesso!");
            attClientList();
            nameField.setText("");
            phoneField.setText("");
        }else{
            successMessage.setText("Os dados não podem estar vazios");
        }


    }
}
