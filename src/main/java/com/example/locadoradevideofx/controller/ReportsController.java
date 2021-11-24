package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReportsController {

    public void returnButton(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("main");
    }
    public void onColBalanceButtonClick(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("collectionBalance");
    }
    public void onMovBalanceButtonClick(ActionEvent e) throws IOException {
        HelloApplication.changeScreen("movementsBalance");
    }
}
