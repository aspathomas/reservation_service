package com.example.reservation_service;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
}
