package com.example.reservation_service;

import java.time.LocalDate;

public class DbClass {

    private DbClass() {}
    public static final String VALUE1 = "foo";
    public static final String VALUE2 = "bar";

    // Les hôtes
    public static final Host host1 = new Host(1,"Jean", "Dupont", "jean.dupont@example.com", "password123");
    public static final Host host2 = new Host(2,"John", "Smith","john.smith@example.com", "password123");
    public static final Host host3 = new Host(3,"Jane", "Doe", "jane.doe@example.com", "password123");
    public static final Host host4 = new Host(4,"Steve", "Johnson", "steve.johnson@example.com", "password123");

    // Les séjours
    public static final Sejour sejour1 =  new Sejour(1,LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 7), 500.0, "Paris", "Appartement romantique", 2, host1);
    public static final Sejour sejour2 =  new Sejour(2, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 17), 700.0, "Londres", "Maison de ville moderne", 4, host2);
    public static final Sejour sejour3 = new Sejour(3, LocalDate.of(2023, 7, 20), LocalDate.of(2023, 7, 27), 1200.0, "New York", "Loft avec vue sur Central Park", 2, host3);
    public static final Sejour sejour4 = new Sejour(4, LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 22), 800.0, "Sydney", "Maison près de la plage", 6, host4);

    // Les utilisateurs
    public static final Traveler traveler1 = new Traveler(1, "Youri", "Novikov", "youri@email.com", "1234");
    public static final Traveler traveler2 = new Traveler(2, "Thomas", "Aspa", "thomas@email.com", "1234");
    public static final Traveler traveler3 = new Traveler(3, "Bob", "Johnson", "bob.johnson@email.com", "password456");
    public static final Traveler traveler4 = new Traveler(4, "Sarah", "Lee", "sarahlee@email.com", "ilovecake");
    public static final Traveler traveler5 = new Traveler(5, "Alex", "Wong", "alex.wong@email.com", "securepassword");


}
