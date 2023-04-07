package com.example.reservation_service;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HostPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Host hote = DbClass.host1;
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
        
        ObservableList<Sejour> exempleSejours = FXCollections.observableArrayList(searchSejourByTitle(hote));
        offeredStaysTable.setItems(exempleSejours);

        TableColumn<Sejour, LocalDate> dateDebutColumn = new TableColumn<>("Date de début");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Sejour, LocalDate> dateFinColumn = new TableColumn<>("Date de fin");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<Sejour, Double> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Sejour, String> lieuColumn = new TableColumn<>("Lieu");
        lieuColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Sejour, String> titreColumn = new TableColumn<>("Titre");
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Sejour, Integer> nombreDePersonnesColumn = new TableColumn<>("Nombre de personnes");
        nombreDePersonnesColumn.setCellValueFactory(new PropertyValueFactory<>("maxGuests"));

        //TableColumn<Sejour, String> hoteColumn = new TableColumn<>("Hôte");
        //hoteColumn.setCellValueFactory(new PropretyValueFactory);
        // Remplacez "fullNameProperty()" par la méthode appropriée dans la classe Host pour obtenir le nom complet de l'hôte

        offeredStaysTable.getColumns().addAll(dateDebutColumn, dateFinColumn, prixColumn, lieuColumn, titreColumn, nombreDePersonnesColumn);

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public List<Sejour> searchSejourByTitle(Host hote) {
        List<Sejour> sejours = DbClass.getSejours();
        List<Sejour> matchingSejours = new ArrayList<>();
        for (Sejour sejour : sejours) {
            if (sejour.getHost() == hote) {
                matchingSejours.add(sejour);
            }
        }
        return matchingSejours;
    }
}
