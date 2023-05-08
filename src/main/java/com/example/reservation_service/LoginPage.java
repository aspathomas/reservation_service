package com.example.reservation_service;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
        // Login form
        Label usernameLabel = new Label("Nom d'utilisateur :");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Mot de passe :");
        PasswordField passwordField = new PasswordField();

        // RadioButtons for choosing between traveler and host
        RadioButton travelerRadioButton = new RadioButton("Voyageur");
        RadioButton hostRadioButton = new RadioButton("Hôte");
        ToggleGroup userTypeToggleGroup = new ToggleGroup();
        travelerRadioButton.setToggleGroup(userTypeToggleGroup);
        hostRadioButton.setToggleGroup(userTypeToggleGroup);
        travelerRadioButton.setSelected(true);

        // Login button
        Button loginButton = new Button("Se connecter");
        loginButton.getStyleClass().add("login-button");
        // Add an event handler for the login button if needed
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

        Scene scene = new Scene(rootLayout, 300, 200);
        scene.getStylesheets().add(getClass().getResource("/com/example/reservation_service/login.css").toExternalForm());
        return scene;
    }

    public Traveler connectTraveler(String usernameField, String passwordField) throws Exception {
        List<Traveler> travelers = DbClass.getTravelers();
        System.out.println(usernameField);
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
