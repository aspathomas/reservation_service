package com.example.reservation_service;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TravelerPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page du voyageur");

        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un séjour...");

        // Liste des séjours
        TableView<Sejour> staysTable = new TableView<>();
        // Configurez la table des séjours avec des colonnes, des données, etc.

        // Panier
        Button viewCartButton = new Button("Voir le panier");
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir le panier"

        // Réservations
        Button viewReservationsButton = new Button("Voir mes réservations");
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir mes réservations"

        // Layout
        VBox topBar = new VBox(searchField);
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);

        HBox bottomBar = new HBox(viewCartButton, viewReservationsButton);
        bottomBar.setSpacing(10);

        VBox rootLayout = new VBox(topBar, staysTable, bottomBar);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
