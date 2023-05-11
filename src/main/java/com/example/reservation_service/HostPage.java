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
import java.util.ArrayList;
import java.util.List;

public class HostPage {

    private MainApp mainApp;
    private Host host;
    private Scene scene;

    private int typePage = 0;

    public HostPage(MainApp mainApp, Host host) {
        this.mainApp = mainApp;
        this.host = host;
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
        Text NomPrenom = new Text(10, 50, host.getFirstName() + ' ' + host.getLastName());


        // Liste des séjours proposés
        TableView<Sejour> offeredStaysTable = new TableView<>();
        // Configurez la table des séjours proposés avec des colonnes, des données, etc.


        // Planning
        Button viewScheduleButton = new Button("Voir le planning");
        // Ajoutez un gestionnaire d'événements pour le bouton "Voir le planning"

        // Layout
        VBox topBar = new VBox(NomPrenom, DeconnecterButton, offeredStaysTable);
        topBar.setPadding(new Insets(10));
        topBar.setSpacing(10);

        HBox bottomBar = new HBox(viewScheduleButton);
        bottomBar.setSpacing(10);

        VBox rootLayout = new VBox(topBar, bottomBar);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);

        ObservableList<Sejour> exempleSejours = FXCollections.observableArrayList(searchSejourByHost(this.host));
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
                            offeredStaysTable.setItems(sejoursBooked());
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

        Button viewReservationsButton = new Button("Voir mes réservations");
        viewScheduleButton.setOnAction(event -> {
            if (this.typePage == 0) {
                offeredStaysTable.setItems(sejoursBooked());
                BookedColumn.setVisible(true);
                viewScheduleButton.setText("Voir mes séjour");
                this.typePage=1;
            } else if (this.typePage == 1) {
                offeredStaysTable.setItems(FXCollections.observableArrayList(searchSejourByHost(this.host)));
                BookedColumn.setVisible(false);
                viewScheduleButton.setText("Voir mon planning");
                this.typePage=0;
            }
        });

        //TableColumn<Sejour, String> hoteColumn = new TableColumn<>("Hôte");
        //hoteColumn.setCellValueFactory(new PropretyValueFactory);
        // Remplacez "fullNameProperty()" par la méthode appropriée dans la classe Host pour obtenir le nom complet de l'hôte

        offeredStaysTable.getColumns().addAll(dateDebutColumn, dateFinColumn, prixColumn, lieuColumn, titreColumn, nombreDePersonnesColumn, BookedColumn);
        BookedColumn.setVisible(false);
        Scene scene = new Scene(rootLayout, 800, 600);
        return scene;
    }

    public List<Sejour> searchSejourByHost(Host hote) {
        List<Sejour> sejours = DbClass.getSejours();
        List<Sejour> matchingSejours = new ArrayList<>();
        for (Sejour sejour : sejours) {
            if (sejour.getHost() == hote) {
                matchingSejours.add(sejour);
            }
        }
        return matchingSejours;
    }

    private void cancelSejour(Sejour sejour){
        DbClass.removeBooking(sejour);
    }

    private ObservableList<Sejour> sejoursBooked(){
        List<Booking> bookings = DbClass.bookings;
        List<Sejour> sejoursBooked = new ArrayList<>();

        for (Booking booking: bookings) {
            if (booking.getSejour().getHost().equals(this.host) && booking.isConfirmed()) {
                sejoursBooked.add(booking.getSejour());
            }
        }
        return FXCollections.observableArrayList(sejoursBooked);
    }
}
