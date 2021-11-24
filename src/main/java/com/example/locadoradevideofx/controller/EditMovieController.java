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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditMovieController {
    @FXML
    private Label successMessage;
    @FXML
    private ChoiceBox moviesList;
    @FXML
    private TextField titleField;
    @FXML
    private ChoiceBox listType;
    @FXML
    private TextField quantityField;

    private List<Movie> movies;

    public void returnButton(ActionEvent e) throws IOException {
        successMessage.setText("");
        HelloApplication.changeScreen("movieRegistration");
    }

    public void initialize() throws SQLException {
        attMovieList();
        listType.getItems().add("Devolução em 24 Horas");
        listType.getItems().add("Devolução em 48 Horas");

        moviesList.setOnAction((event) -> {
            int selectedIndex = moviesList.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1) {
                titleField.setText(movies.get(selectedIndex).title);
                if(movies.get(selectedIndex).type == 1){
                    listType.setValue("Devolução em 24 Horas");
                }else{
                    listType.setValue("Devolução em 48 Horas");
                }
                quantityField.setText(Integer.toString(movies.get(selectedIndex).quantity));
            }
        });

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

    private void attMovieList() throws SQLException {
        movies = Database.getAllMovies();
        moviesList.getItems().clear();

        for(Movie movie : movies){
            moviesList.getItems().add(movie.id+" - "+movie.title);
        }
    }

    public void onEditClickButton(ActionEvent e) throws SQLException {
        if(titleField.getText()!="" && Integer.parseInt(quantityField.getText()) > 0 && listType.getSelectionModel().getSelectedIndex() != -1){
            int selectedIndex = moviesList.getSelectionModel().getSelectedIndex();
            Movie movieToEdit = movies.get(selectedIndex);
            movieToEdit.setTitle(titleField.getText());
            movieToEdit.setType(listType.getSelectionModel().getSelectedIndex() + 1);
            movieToEdit.setQuantity(Integer.parseInt(quantityField.getText()));
            Database.updateMovie(movieToEdit);
            successMessage.setText("Filme editado com sucesso!");
            attMovieList();
            titleField.setText("");
            listType.setValue("");
            quantityField.setText("");
        }else{
            successMessage.setText("Os dados não podem estar vazios");
        }


    }

}
