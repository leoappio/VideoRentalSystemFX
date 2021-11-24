package com.example.locadoradevideofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage globalStage;
    private static Scene mainScene;
    private static Scene clientRegistration;
    private static Scene movieRegistration;
    private static Scene locationMenu;
    private static Scene reportsMenu;
    private static Scene clientNew;
    private static Scene allClients;
    private static Scene deleteClient;
    private static Scene editClient;
    private static Scene movieNew;
    private static Scene allMovies;


    @Override
    public void start(Stage stage) throws IOException {
        globalStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        mainScene = new Scene(fxmlLoader.load(), 500, 300);

        FXMLLoader fxmlClientReg = new FXMLLoader(HelloApplication.class.getResource("client-registration.fxml"));
        clientRegistration = new Scene(fxmlClientReg.load(), 500, 300);

        FXMLLoader fxmlMovieReg = new FXMLLoader(HelloApplication.class.getResource("movie-registration.fxml"));
        movieRegistration = new Scene(fxmlMovieReg.load(), 500, 300);

        FXMLLoader fxmlLocationReg = new FXMLLoader(HelloApplication.class.getResource("location-menu.fxml"));
        locationMenu = new Scene(fxmlLocationReg.load(), 500, 300);

        FXMLLoader fxmlReports = new FXMLLoader(HelloApplication.class.getResource("reports-menu.fxml"));
        reportsMenu = new Scene(fxmlReports.load(), 500, 300);

        FXMLLoader fxmlClientNew = new FXMLLoader(HelloApplication.class.getResource("client-new.fxml"));
        clientNew = new Scene(fxmlClientNew.load(), 500, 300);

        FXMLLoader fxmlAllClients = new FXMLLoader(HelloApplication.class.getResource("all-clients.fxml"));
        allClients = new Scene(fxmlAllClients.load(), 500, 300);

        FXMLLoader fxmlMovieNew = new FXMLLoader(HelloApplication.class.getResource("movie-new.fxml"));
        movieNew = new Scene(fxmlMovieNew.load(), 500, 300);



        stage.setTitle("Locadora de Vídeo");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScreen(String toScreen) throws IOException {
        switch (toScreen){
            case "main":
                globalStage.setScene(mainScene);
                break;
            case "clientRegistration":
                globalStage.setScene(clientRegistration);
                break;
            case "movieRegistration":
                globalStage.setScene(movieRegistration);
                break;
            case "locationMenu":
                globalStage.setScene(locationMenu);
                break;
            case "reportsMenu":
                globalStage.setScene(reportsMenu);
                break;
            case "clientNew":
                globalStage.setScene(clientNew);
                break;
            case "allClients":
                FXMLLoader fxmlAllClients = new FXMLLoader(HelloApplication.class.getResource("all-clients.fxml"));
                allClients = new Scene(fxmlAllClients.load(), 500, 300);
                globalStage.setScene(allClients);
                break;
            case "deleteClient":
                FXMLLoader fxmlDeleteClient = new FXMLLoader(HelloApplication.class.getResource("client-delete.fxml"));
                deleteClient = new Scene(fxmlDeleteClient.load(), 500, 300);
                globalStage.setScene(deleteClient);
                break;
            case "editClient":
                FXMLLoader fxmlEditClient = new FXMLLoader(HelloApplication.class.getResource("client-edit.fxml"));
                editClient = new Scene(fxmlEditClient.load(), 500, 300);
                globalStage.setScene(editClient);
                break;
            case "movieNew":
                globalStage.setScene(movieNew);
                break;
            case "allMovies":
                FXMLLoader fxmlAllMovies = new FXMLLoader(HelloApplication.class.getResource("all-movies.fxml"));
                allMovies = new Scene(fxmlAllMovies.load(), 500, 300);
                globalStage.setScene(allMovies);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}