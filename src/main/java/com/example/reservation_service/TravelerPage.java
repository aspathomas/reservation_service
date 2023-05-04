package com.example.reservation_service;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

    private Scene createScene() {
        //Deconnection
        Button DeconnecterButton = new Button("Déconnecter");
        DeconnecterButton.setOnAction(event -> mainApp.showHomePage());
        //Nom utilisateur
        Text NomPrenom = new Text(10, 50, traveler.getFirstName() + ' ' + traveler.getLastName());
        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un séjour...");


        // Liste des séjours
        TableView<Sejour> staysTable = new TableView<>();
        staysTable.setItems(sejoursLibres());

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

        TableColumn<Sejour, Void> reserveColumn = new TableColumn<>("Réserver");

        Callback<TableColumn<Sejour, Void>, TableCell<Sejour, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Sejour, Void> call(TableColumn<Sejour, Void> param) {
                final TableCell<Sejour, Void> cell = new TableCell<>() {
                    private final Button reserveButton = new Button("Réserver");
                    {
                        reserveButton.setOnAction(event -> {
                            Sejour sejour = getTableView().getItems().get(getIndex());
                            reservedSejour(sejour);
                            staysTable.setItems(sejoursLibres());
                            // Perform reservation action here
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(reserveButton);
                        }
                    }
                };

                return cell;
            }
        };

        reserveColumn.setCellFactory(cellFactory);

        TableColumn<Sejour, Void> BookedColumn = new TableColumn<>("Annuler");

        Callback<TableColumn<Sejour, Void>, TableCell<Sejour, Void>> cellFactory2 = new Callback<>() {
            @Override
            public TableCell<Sejour, Void> call(TableColumn<Sejour, Void> param) {
                final TableCell<Sejour, Void> cell = new TableCell<>() {
                    private final Button cancelButton = new Button("Annuler");
                    {
                        cancelButton.setOnAction(event -> {
                            Sejour sejour = getTableView().getItems().get(getIndex());
                            cancelSejour(sejour);
                            staysTable.setItems(sejoursBooked());
                            // Perform reservation action here
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(cancelButton);
                        }
                    }
                };

                return cell;
            }
        };
        BookedColumn.setCellFactory(cellFactory2);

        staysTable.getColumns().addAll(dateDebutColumn, dateFinColumn, prixColumn, lieuColumn, titreColumn, nombreDePersonnesColumn, reserveColumn, BookedColumn);

        // Configurez la table des séjours avec des colonnes, des données, etc.
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir le panier"

        // Réservations
        Button viewReservationsButton = new Button("Voir mes réservations");
        viewReservationsButton.setOnAction(event -> {
            if (this.typePage == 0) {
                staysTable.setItems(sejoursBooked());
                reserveColumn.setVisible(false);
                BookedColumn.setVisible(true);
                viewReservationsButton.setText("Voir les séjours libres");
                this.typePage=1;
            } else if (this.typePage == 1) {
                staysTable.setItems(sejoursLibres());
                reserveColumn.setVisible(true);
                BookedColumn.setVisible(false);
                viewReservationsButton.setText("Voir mes réservations");
                this.typePage=0;
            }
        });
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir mes réservations"

        // Layout
        VBox topBar = new VBox(NomPrenom, DeconnecterButton, searchField);
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);

        HBox bottomBar = new HBox(viewReservationsButton);
        bottomBar.setSpacing(10);

        VBox rootLayout = new VBox(topBar, staysTable, bottomBar);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);

        searchField.setOnKeyReleased(event -> {
            String query = searchField.getText().toLowerCase();
            List<Sejour> matchingSejours = searchSejourByTitle(sejoursLibres(), query);
            staysTable.setItems(FXCollections.observableArrayList(matchingSejours));
        });

        Scene scene = new Scene(rootLayout, 800, 600);
        return scene;
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
}
