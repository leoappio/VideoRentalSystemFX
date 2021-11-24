package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ClientController {

    @FXML
    private Label successMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("clientRegistration");
    }

    public void onCreateButton(ActionEvent e) throws SQLException {
        if(nameField.getText()!="" && phoneField.getText()!= "") {
            Client client = new Client(nameField.getText(), phoneField.getText());
            Database.insertClient(client);
            nameField.setText("");
            phoneField.setText("");
            successMessage.setText("Cliente cadastrado com sucesso!");
        }else{
            successMessage.setText("Preencha todos os campos!");
        }
    }
}
