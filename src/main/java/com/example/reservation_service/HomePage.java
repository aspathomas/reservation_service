package com.example.reservation_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class HomePage {
    private MainApp mainApp;
    private Scene scene;

    public HomePage(MainApp mainApp) {
        this.mainApp = mainApp;
        this.scene = createScene();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene createScene() {
        BorderPane root = new BorderPane();

        VBox topContainer = new VBox(10); // Add spacing between elements
        topContainer.setStyle("-fx-padding: 10;"); // Add padding to the container

        HBox searchBarContainer = new HBox();
        TextField searchBar = new TextField();
        searchBar.setPromptText("Recherchez un séjour");
        searchBar.getStyleClass().add("search-bar"); // Add a CSS class to the search bar
        searchBarContainer.getChildren().addAll(searchBar);
        searchBarContainer.setAlignment(Pos.CENTER);

        HBox loginButtonsContainer = new HBox(10); // Add spacing between buttons
        Button loginButton = new Button("Login Page");
        loginButton.getStyleClass().add("login-button"); // Add a CSS class to the login button
        loginButton.setOnAction(event -> mainApp.showLoginPage());
        loginButtonsContainer.getChildren().addAll(loginButton);
        loginButtonsContainer.setAlignment(Pos.CENTER);

        topContainer.getChildren().addAll(searchBarContainer, loginButtonsContainer);

// Replace TableView with ListView
        ListView<Sejour> staysList = new ListView<>();
        staysList.getStyleClass().add("stays-list"); // Add a CSS class to the list view
        ObservableList<Sejour> exempleSejours = genererExempleSejours();
        staysList.setItems(exempleSejours);

        staysList.setCellFactory(param -> new SejourListCell());

        root.setTop(topContainer);
        root.setCenter(staysList);

        searchBar.setOnKeyReleased(event -> {
            String query = searchBar.getText().toLowerCase();
            List<Sejour> matchingSejours = searchSejourByTitle(exempleSejours, query);
            staysList.setItems(FXCollections.observableArrayList(matchingSejours));
        });

// Create a new scene and add a stylesheet
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/reservation_service/home.css").toExternalForm());
        return scene;

    }


    private ObservableList<Sejour> genererExempleSejours() {
        List<Sejour> sejours = DbClass.getSejours();

        return FXCollections.observableArrayList(sejours);
    }

    public List<Sejour> searchSejourByTitle(List<Sejour> sejours, String titleQuery) {
        List<Sejour> matchingSejours = new ArrayList<>();
        for (Sejour sejour : sejours) {
            if (sejour.getTitle().toLowerCase().contains(titleQuery)) {
                matchingSejours.add(sejour);
            }
        }
        return matchingSejours;
    }
}
