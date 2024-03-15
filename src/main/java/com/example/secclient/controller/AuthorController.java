package com.example.secclient.controller;

import com.example.secclient.entity.Author;
import com.example.secclient.service.entity.AuthorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AuthorController {

    private final AuthorService service = new AuthorService();

    @FXML
    private ListView<Author> dataList;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getAuthors());
    }

    @FXML
    void addNewAuthor(ActionEvent event) {

    }

    @FXML
    void cancelEvent(ActionEvent event) {

    }

    @FXML
    void deleteAuthor(ActionEvent event) {

    }

}

