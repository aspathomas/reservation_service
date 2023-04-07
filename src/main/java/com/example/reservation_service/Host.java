package com.example.reservation_service;
import java.io.Serializable;

public class Host extends User implements Serializable {
    public Host(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    // Vous pouvez ajouter des méthodes et des propriétés spécifiques à l'hôte ici, si nécessaire
}

