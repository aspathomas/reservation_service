package com.example.reservation_service;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HostPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Page de l'hôte");

        // Liste des séjours proposés
        TableView<Sejour> offeredStaysTable = new TableView<>();
        // Configurez la table des séjours proposés avec des colonnes, des données, etc.

        // Demandes de réservation
        Button viewBookingRequestsButton = new Button("Voir les demandes de réservation");
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir les demandes de réservation"

        // Planning
        Button viewScheduleButton = new Button("Voir le planning");
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir le planning"

        // Layout
        VBox topBar = new VBox(offeredStaysTable);
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);

        HBox bottomBar = new HBox(viewBookingRequestsButton, viewScheduleButton);
        bottomBar.setSpacing(10);

        VBox rootLayout = new VBox(topBar, bottomBar);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
