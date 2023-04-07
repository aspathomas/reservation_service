package com.example.reservation_service;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

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

    public void showTravelerPage() {
        //TravelerPage travelerPage = new TravelerPage(this);
        //primaryStage.setScene(travelerPage.getScene());
        //primaryStage.show();
    }

    public void showHostPage() {
        //HostPage hostPage = new HostPage(this);
        //primaryStage.setScene(hostPage.getScene());
        //primaryStage.show();
    }
}
