package com.example.secclient.controller;

import com.example.secclient.entity.City;
import com.example.secclient.entity.Genre;
import com.example.secclient.service.entity.GenreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GenreController {

    private final GenreService service = new GenreService();
    private boolean addFlag = true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alert_bad = new Alert(Alert.AlertType.ERROR);


    @FXML
    private ListView<Genre> dataList;

    @FXML
    private TextField textName;

    @FXML
    private Button buttonAdd;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        try {
            service.getAll();
            dataList.setItems(service.getGenres());
        } catch (Exception e ) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Отсутствует подключение к серверу ");
            alert.setContentText("Обратитесь в тех.поддержку.....");
            alert.showAndWait();
            dialogStage.close();
        }
    }

    @FXML
    void addNewGenre(ActionEvent event) {
        Genre genre = new Genre();
        String errorMesage = "";

        if ( !textName.getText().isEmpty() & textName.getText().matches("[А-Я][а-я]{1,20}")) {
            genre.setTitle(textName.getText());
        } else {
            errorMesage += "\nполе Жанр должно выглядеть так: Классика";
        }
        if (addFlag) {
            try {
                service.add(genre);
                alert.setTitle("Успешно");
                alert.setHeaderText("Данные добавленны");
                alert.showAndWait();

                textName.clear();
                dataList.getItems().clear();
                initialize();
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        } else {
            genre.setId(dataList.getSelectionModel().getSelectedItem().getId());
            try {
                service.update(genre, dataList.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                if (!errorMesage.isEmpty()) {
                    alert_bad.setTitle("Ошибка");
                    alert_bad.setHeaderText("Ошибка ввода!");
                    alert_bad.setContentText(errorMesage);
                    alert_bad.showAndWait();
                } else {
                    alert_bad.setTitle("Ошибка");
                    alert_bad.setHeaderText("Ошибка подключения!");
                    alert_bad.setContentText("Подключитесь к серверу...");
                    alert_bad.showAndWait();
                }
            }
        }

    }

    // Выбор жанра по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                buttonAdd.setText("Изменить");
                addFlag = false;
                Genre tempGenre = dataList.getSelectionModel().getSelectedItem();
                textName.setText(tempGenre.getTitle());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        buttonAdd.setText("Добавить");
        addFlag = true;
        textName.clear();
    }

    @FXML
    void deleteGenre(ActionEvent event) {
        Genre tempGenre = dataList.getSelectionModel().getSelectedItem();
        if (addFlag) {
            textName.setText(tempGenre.getTitle());
            try {
                service.delete(tempGenre);

                alert.setTitle("Успешно");
                alert.setHeaderText("Данные были удалены!");
                alert.showAndWait();

                textName.clear();
                dataList.getItems().clear();
                dataList.getSelectionModel().clearSelection();
                initialize();
            } catch (Exception e ){
                textName.clear();
                dataList.getItems().clear();
                dataList.getSelectionModel().clearSelection();
                initialize();
            }
        } else {
            alert.setTitle("Успешно");
            alert.setHeaderText("Данные не выбраны");
            alert.showAndWait();
        }

    }

}

