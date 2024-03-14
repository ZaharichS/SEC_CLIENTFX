package com.example.secclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class BookController {

    @FXML
    private ComboBox<?> comboBoxAuthor;

    @FXML
    private ComboBox<?> comboBoxGenre;

    @FXML
    private ComboBox<?> comboBoxPublisher;

    @FXML
    private TextField textName;

    @FXML
    private TextField textYear;

    @FXML
    void addNewBook(ActionEvent event) {

    }

    @FXML
    void cancelEvent(ActionEvent event) {

    }

}
