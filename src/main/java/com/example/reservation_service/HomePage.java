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

        sejours.add(DbClass.sejour1);
        sejours.add(DbClass.sejour2);
        sejours.add(DbClass.sejour3);
        sejours.add(DbClass.sejour4);

        return FXCollections.observableArrayList(sejours);
    }

    private ObservableList<Sejour> searchSejourByTitle(String title) {
        ObservableList<Sejour> sejours = genererExempleSejours();
        List<Sejour> matchingSejours = new ArrayList<>();
        for (Sejour sejour : sejours) {
            if (sejour.getTitle().contains(title)) {
                matchingSejours.add(sejour);
            }
        }
        return FXCollections.observableArrayList(matchingSejours);
    }
}
