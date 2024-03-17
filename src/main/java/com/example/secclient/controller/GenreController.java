package com.example.secclient.controller;

import com.example.secclient.entity.City;
import com.example.secclient.entity.Genre;
import com.example.secclient.service.entity.GenreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GenreController {

    private final GenreService service = new GenreService();
    private boolean addFlag = true;

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
        Genre genre = new Genre();
        genre.setTitle(textName.getText());
        if (addFlag) {
            service.add(genre);
        } else {
            genre.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.update(genre, dataList.getSelectionModel().getSelectedItem());
        }
        textName.clear();
    }

    // Выбор жанра по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                addFlag = false;
                Genre tempGenre = dataList.getSelectionModel().getSelectedItem();
                textName.setText(tempGenre.getTitle());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        addFlag = true;
        textName.clear();
    }

    @FXML
    void deleteGenre(ActionEvent event) {
        Genre tempGenre = dataList.getSelectionModel().getSelectedItem();
        textName.setText(tempGenre.getTitle());
        if (addFlag) {
            service.delete(tempGenre);
            dataList.getSelectionModel().clearSelection();
        } else {
            tempGenre.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.delete(tempGenre);
        }
        textName.clear();
    }

}

