package com.example.secclient.controller;

import com.example.secclient.MainApplication;
import com.example.secclient.entity.Author;
import com.example.secclient.service.entity.AuthorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AuthorController {

    private final AuthorService service = new AuthorService();
    private boolean addFlag = true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alert_bad = new Alert(Alert.AlertType.ERROR);

    @FXML
    private ListView<Author> dataList;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private Button buttonAdd;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getAuthors());
    }

    @FXML
    void addNewAuthor(ActionEvent event) {
        Author author = new Author();
        String errorMesage = "";
        if ( !textLastName.getText().isEmpty() & textLastName.getText().matches("[А-Я][а-я]{1,20}")) {
            author.setLastname(textLastName.getText());
        } else {
            errorMesage += "\nполе Отчество должно выглядеть так: Сергеевич";
        }
        if ( !textName.getText().isEmpty() & textName.getText().matches("[А-Я][а-я]{1,20}")) {
            author.setName(textName.getText());
        } else {
            errorMesage += "\nполе Имя должно выглядеть так: Александр";
        }
        if ( !textSurname.getText().isEmpty() & textSurname.getText().matches("[А-Я][а-я]{1,20}")) {
            author.setSurname(textSurname.getText());
        } else {
            errorMesage += "\nполе Фамилия должно выглядеть так: Пушкин";
        }

        if (addFlag) {
            try {
                service.add(author);
                alert.setTitle("Успешно");
                alert.setHeaderText("Данные добавленны");
                alert.showAndWait();

                textLastName.clear();
                textName.clear();
                textSurname.clear();
                dataList.getItems().clear();
                initialize();
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        } else {
            author.setId(dataList.getSelectionModel().getSelectedItem().getId());
            try {
                service.update(author, dataList.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        }
    }


    // Выбор автора по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                buttonAdd.setText("Изменить");
                addFlag = false;
                Author tempAuthor = dataList.getSelectionModel().getSelectedItem();

                textName.setText(tempAuthor.getName());
                textLastName.setText(tempAuthor.getName());
                textSurname.setText(tempAuthor.getSurname());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        buttonAdd.setText("Добавить");
        addFlag = true;
        textLastName.clear();
        textName.clear();
        textSurname.clear();
    }

    @FXML
    void deleteAuthor(ActionEvent event) {
        Author tempAuthor = dataList.getSelectionModel().getSelectedItem();
        textLastName.setText(tempAuthor.getLastname());
        textName.setText(tempAuthor.getName());
        textSurname.setText(tempAuthor.getSurname());
        if (addFlag) {
            service.delete(tempAuthor);
        } else {
            tempAuthor.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.delete(tempAuthor);
        }
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные были удалены!");
        alert.showAndWait();

        textLastName.clear();
        textName.clear();
        textSurname.clear();
        dataList.getItems().clear();
        initialize();
    }

}

