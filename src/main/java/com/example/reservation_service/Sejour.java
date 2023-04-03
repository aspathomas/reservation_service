package com.example.reservation_service;

import java.time.LocalDate;

public class Sejour {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private String lieu;
    private String titre;
    private int nombreDePersonnes;
    private String hote;

    public Sejour(LocalDate dateDebut, LocalDate dateFin, double prix, String lieu, String titre, int nombreDePersonnes, String hote) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.lieu = lieu;
        this.titre = titre;
        this.nombreDePersonnes = nombreDePersonnes;
        this.hote = hote;
    }

    // Getters et setters pour les propriétés

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNombreDePersonnes() {
        return nombreDePersonnes;
    }

    public void setNombreDePersonnes(int nombreDePersonnes) {
        this.nombreDePersonnes = nombreDePersonnes;
    }

    public String getHote() {
        return hote;
    }

    public void setHote(String hote) {
        this.hote = hote;
    }
}
