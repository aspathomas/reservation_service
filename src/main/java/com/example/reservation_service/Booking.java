package com.example.reservation_service;

import java.time.LocalDate;
import java.io.Serializable;

public class Booking implements Serializable {
    private Traveler traveler;
    private Sejour sejour;
    private LocalDate bookingDate;
    private boolean isConfirmed;

    public Booking(Traveler traveler, Sejour sejour, LocalDate bookingDate, boolean isConfirmed) {
        this.traveler = traveler;
        this.sejour = sejour;
        this.bookingDate = bookingDate;
        this.isConfirmed = isConfirmed;
    }
    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
