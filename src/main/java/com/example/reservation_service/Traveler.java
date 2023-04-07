package com.example.reservation_service;
import java.io.Serializable;

// Traveler.java
public class Traveler extends User implements Serializable {
    public Traveler(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    // Vous pouvez ajouter des méthodes et des propriétés spécifiques au voyageur ici, si nécessaire
}

