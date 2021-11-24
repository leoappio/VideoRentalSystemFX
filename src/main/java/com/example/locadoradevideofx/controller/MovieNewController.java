package com.example.locadoradevideofx.controller;

import com.example.locadoradevideofx.HelloApplication;
import com.example.locadoradevideofx.model.Client;
import com.example.locadoradevideofx.model.Database;
import com.example.locadoradevideofx.model.Movie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class MovieNewController {

    @FXML
    private Label successMessage;
    @FXML
    private TextField titleField;
    @FXML
    private TextField quantityField;
    @FXML
    private ChoiceBox listType;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("movieRegistration");
    }

    public void onCreateButton(ActionEvent e) throws SQLException {
        if(titleField.getText()!="" && quantityField.getText()!= "" && listType.getSelectionModel().getSelectedIndex() != -1) {
            int type = listType.getSelectionModel().getSelectedIndex() + 1;
            Movie movie = new Movie(titleField.getText(),type,Integer.parseInt(quantityField.getText()));
            Database.insertMovie(movie);
            titleField.setText("");
            quantityField.setText("");
            listType.setValue("");
            successMessage.setText("Filme cadastrado com sucesso!");
        }else{
            successMessage.setText("Preencha todos os campos!");
        }
    }

    public void initialize(){
        listType.getItems().add("Devolução em 24 Horas");
        listType.getItems().add("Devolução em 48 Horas");
        quantityField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantityField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }
}
