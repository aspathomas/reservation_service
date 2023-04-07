package com.example.reservation_service;

import java.time.LocalDate;
import java.io.Serializable;

public class Booking implements Serializable {
    private int id;
    private Traveler traveler;
    private Sejour stay;
    private LocalDate bookingDate;
    private boolean isConfirmed;

    public Booking(int id, Traveler traveler, Sejour stay, LocalDate bookingDate, boolean isConfirmed) {
        this.id = id;
        this.traveler = traveler;
        this.stay = stay;
        this.bookingDate = bookingDate;
        this.isConfirmed = isConfirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public Sejour getStay() {
        return stay;
    }

    public void setStay(Sejour stay) {
        this.stay = stay;
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
