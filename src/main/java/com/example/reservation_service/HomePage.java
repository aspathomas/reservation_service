package com.example.reservation_service;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {


        BorderPane root = new BorderPane();

        VBox topContainer = new VBox();
        HBox searchBarContainer = new HBox();
        TextField searchBar = new TextField();
        searchBar.setPromptText("Recherchez un séjour");
        searchBarContainer.getChildren().addAll(searchBar);
        searchBarContainer.setAlignment(Pos.CENTER);

        HBox loginButtonsContainer = new HBox();
        Button travelerLoginButton = new Button("Connexion Voyageur");
        Button hostLoginButton = new Button("Connexion Hôte");
        loginButtonsContainer.getChildren().addAll(travelerLoginButton, hostLoginButton);
        loginButtonsContainer.setSpacing(10);
        loginButtonsContainer.setAlignment(Pos.CENTER);

        topContainer.getChildren().addAll(searchBarContainer, loginButtonsContainer);

        TableView<Sejour> staysTable = new TableView<>(); // Replace Stay with your Stay class
        // Add columns to the staysTable here
        ObservableList<Sejour> exempleSejours = genererExempleSejours();
        System.out.println(exempleSejours.size());
        staysTable.setItems(exempleSejours);


        TableColumn<Sejour, LocalDate> dateDebutColumn = new TableColumn<>("Date de début");
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        TableColumn<Sejour, LocalDate> dateFinColumn = new TableColumn<>("Date de fin");
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        TableColumn<Sejour, Double> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn<Sejour, String> lieuColumn = new TableColumn<>("Lieu");
        lieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));

        TableColumn<Sejour, String> titreColumn = new TableColumn<>("Titre");
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<Sejour, Integer> nombreDePersonnesColumn = new TableColumn<>("Nombre de personnes");
        nombreDePersonnesColumn.setCellValueFactory(new PropertyValueFactory<>("nombreDePersonnes"));

        TableColumn<Sejour, String> hoteColumn = new TableColumn<>("Hôte");
        hoteColumn.setCellValueFactory(new PropertyValueFactory<>("hote"));

        staysTable.getColumns().addAll(dateDebutColumn, dateFinColumn, prixColumn, lieuColumn, titreColumn, nombreDePersonnesColumn, hoteColumn);

        root.setTop(topContainer);
        root.setCenter(staysTable);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application de réservation de séjours");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<Sejour> genererExempleSejours() {
        List<Sejour> sejours = new ArrayList<>();

        sejours.add(new Sejour(LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), 500.0, "Paris", "Appartement romantique", 2, "Jean Dupont"));
        sejours.add(new Sejour(LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 17), 700.0, "Londres", "Maison de ville moderne", 4, "John Smith"));
        sejours.add(new Sejour(LocalDate.of(2023, 7, 20), LocalDate.of(2023, 7, 27), 1200.0, "New York", "Loft avec vue sur Central Park", 2, "Jane Doe"));
        sejours.add(new Sejour(LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 22), 800.0, "Sydney", "Maison près de la plage", 6, "Steve Johnson"));

        return FXCollections.observableArrayList(sejours);
    }

}
