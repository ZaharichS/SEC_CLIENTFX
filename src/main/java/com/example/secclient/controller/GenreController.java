package com.example.secclient.controller;

import com.example.secclient.entity.Genre;
import com.example.secclient.service.entity.GenreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GenreController {

    private final GenreService service = new GenreService();

    @FXML
    private ListView<Genre> dataList;

    @FXML
    private TextField textName;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getGenres());
    }

    @FXML
    void addNewGenre(ActionEvent event) {

    }

    @FXML
    void cancelEvent(ActionEvent event) {

    }

    @FXML
    void deleteGenre(ActionEvent event) {

    }

}

