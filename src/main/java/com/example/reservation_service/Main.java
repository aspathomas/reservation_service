package com.example.reservation_service;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Sejour> stays = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();

        // Ajoutez des objets Stay et Booking à leurs listes respectives

        // Écrire les données dans des fichiers
        FileManager.writeDataToFile(stays, "stays.dat");
        FileManager.writeDataToFile(bookings, "bookings.dat");

        // Lire les données à partir des fichiers
        List<Sejour> readStays = FileManager.readDataFromFile("stays.dat");
        List<Booking> readBookings = FileManager.readDataFromFile("bookings.dat");
    }
}
