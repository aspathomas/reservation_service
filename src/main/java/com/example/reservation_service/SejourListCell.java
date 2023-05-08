package com.example.reservation_service;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

class SejourListCell extends ListCell<Sejour> {
    @Override
    protected void updateItem(Sejour item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Create a block layout for each Sejour
            VBox block = new VBox(5);
            block.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5;");

            Label titleLabel = new Label(item.getTitle());
            titleLabel.setStyle("-fx-font-weight: bold;");
            Label locationLabel = new Label("Location: " + item.getLocation());
            Label guestsLabel = new Label("Max Guests: " + item.getMaxGuests());

            block.getChildren().addAll(titleLabel, locationLabel, guestsLabel);
            setGraphic(block);
        }
    }
}
