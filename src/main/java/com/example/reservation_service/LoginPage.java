package com.example.reservation_service;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    private MainApp mainApp;
    private Scene scene;

    public LoginPage(MainApp mainApp) {
        this.mainApp = mainApp;
        this.scene = createScene();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene createScene() {
        // Formulaire de connexion
        Label usernameLabel = new Label("Nom d'utilisateur :");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Mot de passe :");
        PasswordField passwordField = new PasswordField();

        // RadioButtons pour choisir entre voyageur et hôte
        RadioButton travelerRadioButton = new RadioButton("Voyageur");
        RadioButton hostRadioButton = new RadioButton("Hôte");
        ToggleGroup userTypeToggleGroup = new ToggleGroup();
        travelerRadioButton.setToggleGroup(userTypeToggleGroup);
        hostRadioButton.setToggleGroup(userTypeToggleGroup);
        travelerRadioButton.setSelected(true);
        // Bouton de connexion
        Button loginButton = new Button("Se connecter");
        // Ajoutez un gestionnaire d'événements pour le bouton de connexion si nécessaire
        loginButton.setOnAction(event -> {
            try {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (travelerRadioButton.isSelected()) {
                    Traveler traveler = connectTraveler(username, password);
                    mainApp.showTravelerPage(traveler);
                } else {
                    Host host = connectHost(username, password);
                    mainApp.showHostPage(host);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        // Layout
        VBox formLayout = new VBox(usernameLabel, usernameField, passwordLabel, passwordField);
        formLayout.setPadding(new Insets(10));
        formLayout.setSpacing(10);

        HBox radioButtonsLayout = new HBox(travelerRadioButton, hostRadioButton);
        radioButtonsLayout.setSpacing(10);
        radioButtonsLayout.setAlignment(Pos.CENTER);

        VBox rootLayout = new VBox(formLayout, radioButtonsLayout, loginButton);
        rootLayout.setPadding(new Insets(10));
        rootLayout.setSpacing(10);
        rootLayout.setAlignment(Pos.CENTER);

        return new Scene(rootLayout, 300, 200);
    }

    public Traveler connectTraveler(String usernameField, String passwordField) throws Exception {
        List<Traveler> travelers = DbClass.getTravelers();
        usernameField = "Youri";
        for (Traveler traveler : travelers) {
            if (traveler.getFirstName().equals(usernameField)) {
                return traveler;
            }
        }
        throw new Exception("Voyageur pas trouvé");
    }

    public Host connectHost(String usernameField, String passwordField) throws Exception {
        List<Host> hosts = DbClass.getHosts();
        for (Host host : hosts) {
            if (host.getFirstName().equals(usernameField)) {
                return host;
            }
        }
        throw new Exception("Hôte pas trouvé");
    }
}
