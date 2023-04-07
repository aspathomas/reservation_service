package com.example.reservation_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
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

        VBox topContainer = new VBox();
        HBox searchBarContainer = new HBox();
        TextField searchBar = new TextField();
        searchBar.setPromptText("Recherchez un séjour");
        searchBarContainer.getChildren().addAll(searchBar);
        searchBarContainer.setAlignment(Pos.CENTER);

        HBox loginButtonsContainer = new HBox();
        Button loginButton = new Button("Login Page");
        loginButton.setOnAction(event -> mainApp.showLoginPage());
        loginButtonsContainer.getChildren().addAll(loginButton);
        loginButtonsContainer.setSpacing(10);
        loginButtonsContainer.setAlignment(Pos.CENTER);

        topContainer.getChildren().addAll(searchBarContainer, loginButtonsContainer);

        TableView<Sejour> staysTable = new TableView<>();
        ObservableList<Sejour> exempleSejours = genererExempleSejours();
        staysTable.setItems(exempleSejours);

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

        staysTable.getColumns().addAll(dateDebutColumn, dateFinColumn, prixColumn, lieuColumn, titreColumn, nombreDePersonnesColumn);

        root.setTop(topContainer);
        root.setCenter(staysTable);

        return new Scene(root, 800, 600);
    }


    private ObservableList<Sejour> genererExempleSejours() {
        List<Sejour> sejours = new ArrayList<>();

        Host host1 = new Host(1,"Jean", "Dupont", "jean.dupont@example.com", "password123");
        Host host2 = new Host(2,"John", "Smith","john.smith@example.com", "password123");
        Host host3 = new Host(3,"Jane", "Doe", "jane.doe@example.com", "password123");
        Host host4 = new Host(4,"Steve", "Johnson", "steve.johnson@example.com", "password123");

        sejours.add(new Sejour(1, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), 500.0, "Paris", "Appartement romantique", 2, host1));
        sejours.add(new Sejour(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 17), 700.0, "Londres", "Maison de ville moderne", 4, host2));
        sejours.add(new Sejour(3, LocalDate.of(2023, 7, 20), LocalDate.of(2023, 7, 27), 1200.0, "New York", "Loft avec vue sur Central Park", 2, host3));
        sejours.add(new Sejour(4, LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 22), 800.0, "Sydney", "Maison près de la plage", 6, host4));

        return FXCollections.observableArrayList(sejours);
    }

}
