package com.example.secclient.controller;

import com.example.secclient.MainApplication;
import com.example.secclient.entity.Author;
import com.example.secclient.service.entity.AuthorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AuthorController {

    private final AuthorService service = new AuthorService();
    private boolean addFlag = true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
        Author author = new Author();
        author.setLastname(textLastName.getText());
        author.setName(textName.getText());
        author.setSurname(textSurname.getText());
        if (addFlag) {
            service.add(author);
        } else {
            author.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.update(author, dataList.getSelectionModel().getSelectedItem());
        }
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();

        textLastName.clear();
        textName.clear();
        textSurname.clear();
    }

    // Выбор автора по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                addFlag = false;
                Author tempAuthor = dataList.getSelectionModel().getSelectedItem();
                textLastName.setText(tempAuthor.getLastname());
                textName.setText(tempAuthor.getName());
                textSurname.setText(tempAuthor.getSurname());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
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
        alert.setContentText("Перезайдите в регистрацию.");
        alert.showAndWait();

        textLastName.clear();
        textName.clear();
        textSurname.clear();
    }

}

