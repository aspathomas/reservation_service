package com.example.reservation_service;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private Booking booking;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showHomePage();
    }

    public void showHomePage() {
        HomePage homePage = new HomePage(this);
        primaryStage.setScene(homePage.getScene());
        primaryStage.show();
    }

    public void showLoginPage() {
        LoginPage loginPage = new LoginPage(this);
        primaryStage.setScene(loginPage.getScene());
        primaryStage.show();
    }

    public void showTravelerPage(Traveler traveler) {
        TravelerPage travelerPage = new TravelerPage(this, traveler);
        primaryStage.setScene(travelerPage.getScene());
        primaryStage.show();
    }

    public void showHostPage(Host host) {
        HostPage hostPage = new HostPage(this, host);
        primaryStage.setScene(hostPage.getScene());
        primaryStage.show();
    }
}
