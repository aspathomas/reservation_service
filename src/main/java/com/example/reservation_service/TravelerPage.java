package com.example.reservation_service;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelerPage {

    private MainApp mainApp;
    private Traveler traveler;
    private Scene scene;

    private int typePage = 0;

    public TravelerPage(MainApp mainApp, Traveler traveler) {
        this.mainApp = mainApp;
        this.traveler = traveler;
        this.scene = createScene();
    }

    public Scene getScene() {
        return scene;
    }
    private ObservableList<Sejour> allSejours = sejoursLibres();

    private GridPane staysGrid;
    private Scene createScene() {
        // Deconnection
        Button DeconnecterButton = new Button("Déconnecter");
        DeconnecterButton.getStyleClass().add("logout-button");
        DeconnecterButton.setOnAction(event -> mainApp.showHomePage());


        // User name
        Text NomPrenom = new Text(10, 50, traveler.getFirstName() + ' ' + traveler.getLastName());
        NomPrenom.getStyleClass().add("user-name");

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un séjour...");
        searchField.getStyleClass().add("search-field");


        // Liste des séjours
        //allSejours = FXCollections.observableArrayList(DbClass.getSejours());


        staysGrid = new GridPane();
        staysGrid.getStyleClass().add("stays-grid"); // Add a CSS class to the grid
        updateStaysGrid(sejoursLibres(), 0);
        // Configurez la table des séjours avec des colonnes, des données, etc.
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir le panier"

        Button viewReservationsButton = new Button("Voir mes réservations");
        viewReservationsButton.setOnAction(event -> {
            if (this.typePage == 0) {
                updateStaysGrid(sejoursBooked(), 1);
                viewReservationsButton.setText("Voir les séjours libres");
                this.typePage=1;
            } else if (this.typePage == 1) {
                updateStaysGrid(sejoursLibres(), 0);
                viewReservationsButton.setText("Voir mes réservations");
                this.typePage=0;
            }
        });



        // Ajoutez un gestionnaire d'événements pour le bouton "Voir mes réservations"

        // Layout
        HBox userNameAndLogout = new HBox(NomPrenom, DeconnecterButton);
        userNameAndLogout.setSpacing(15);
        userNameAndLogout.setAlignment(Pos.CENTER);

        HBox searchAndViewReservations = new HBox(searchField, viewReservationsButton);
        searchAndViewReservations.setSpacing(10);
        searchAndViewReservations.setAlignment(Pos.CENTER);

        VBox topBar = new VBox(userNameAndLogout, searchAndViewReservations);
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);
        topBar.setAlignment(Pos.CENTER);
        topBar.getStyleClass().add("top-bar");

        ScrollPane staysScrollPane = new ScrollPane(staysGrid);
        staysScrollPane.setFitToWidth(true);

        VBox rootLayout = new VBox(topBar, staysScrollPane);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);
        rootLayout.getStyleClass().add("root-layout");


        searchField.setOnKeyReleased(event -> {
            String query = searchField.getText().toLowerCase();
            List<Sejour> matchingSejours = searchSejourByTitle(sejoursLibres(), query);
            updateStaysGrid(FXCollections.observableArrayList(matchingSejours), 0);
        });

        Scene scene = new Scene(rootLayout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/reservation_service/traveler.css").toExternalForm());
        return scene;
    }

    private ObservableList<Sejour> genererExempleSejours() {
        List<Sejour> sejoursLibres = new ArrayList<>();
        List<Booking> bookings = DbClass.bookings;

        for (Sejour sejour : allSejours) {
            boolean isReserved = false;

            listerBooking: {
                for (Booking booking : bookings) {
                    if (booking.getSejour().equals(sejour)) {
                        if (booking.isConfirmed()) {
                            isReserved = true;
                        }
                        break listerBooking;
                    }
                }
            }

            if (!isReserved) {
                sejoursLibres.add(sejour);
            }
        }
        return FXCollections.observableArrayList(sejoursLibres);
    }

    private ObservableList<Sejour> sejoursLibres() {
        List<Sejour> sejours = DbClass.getSejours();
        List<Booking> bookings = DbClass.bookings;
        List<Sejour> sejoursLibres = new ArrayList<>();

        for (Sejour sejour: sejours) {
            boolean isReserved = false;

            listerBooking: {
                for (Booking booking: bookings) {
                    if (booking.getSejour().equals(sejour)) {
                        if (booking.isConfirmed()) {
                            isReserved = true;
                        }
                        break listerBooking;
                    }
                }
            }

            if (!isReserved) {
                sejoursLibres.add(sejour);
            }

        }
        return FXCollections.observableArrayList(sejoursLibres);
    }

    private void reservedSejour(Sejour sejour){
        Booking booking = new Booking(this.traveler, sejour, LocalDate.now(), true);
        DbClass.addBooking(booking);
    }

    private void cancelSejour(Sejour sejour){
        DbClass.removeBooking(sejour);
    }

    private ObservableList<Sejour> sejoursBooked(){
        List<Booking> bookings = DbClass.bookings;
        List<Sejour> sejoursBooked = new ArrayList<>();

        for (Booking booking: bookings) {
            if (booking.getTraveler().equals(this.traveler)) {
                sejoursBooked.add(booking.getSejour());
            }
        }
        return FXCollections.observableArrayList(sejoursBooked);
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

    private VBox createStayBox(Sejour stay, ObservableList<Sejour> staysList, int typePage) {
        VBox stayBox = new VBox(10);
        stayBox.getStyleClass().add("stay-box");

        Label titleLabel = new Label(stay.getTitle());
        titleLabel.getStyleClass().add("stay-title");

        Label locationLabel = new Label(stay.getLocation());
        locationLabel.getStyleClass().add("stay-location");

        Label priceLabel = new Label("Prix: " + stay.getPrice());
        priceLabel.getStyleClass().add("stay-price");

        Button reserveButton = new Button(typePage == 0 ? "Réserver" : "Annuler");
        reserveButton.getStyleClass().add("stay-reserve-button");
        reserveButton.setOnAction(event -> {
            if (typePage == 0) {
                reservedSejour(stay);
                updateStaysGrid(sejoursLibres(), 0);
            } else {
                cancelSejour(stay);
                updateStaysGrid(sejoursBooked(), 1);
            }
        });

        stayBox.getChildren().addAll(titleLabel, locationLabel, priceLabel, reserveButton);

        return stayBox;
    }



    private void updateStaysGrid(List<Sejour> staysList, int typePage) {
        staysGrid.getChildren().clear();
        for (int i = 0; i < staysList.size(); i++) {
            Sejour stay = staysList.get(i);
            VBox stayBox = createStayBox(stay, FXCollections.observableArrayList(staysList), typePage);
            staysGrid.add(stayBox, i % 3, i / 3);
        }
    }








}
