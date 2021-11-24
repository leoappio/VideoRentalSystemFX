package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ClientRegistrationController {

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("main");
    }

    public void onCreateButtonClick(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("clientNew");
    }

    public void onAllClientsButtonClick(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("allClients");
    }

    public void onDeleteClientButtonClick(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("deleteClient");
    }

}
