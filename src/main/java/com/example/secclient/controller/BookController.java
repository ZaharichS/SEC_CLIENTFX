package com.example.secclient.controller;

import com.example.secclient.MainApplication;
import com.example.secclient.MainController;
import com.example.secclient.entity.Author;
import com.example.secclient.entity.Book;
import com.example.secclient.entity.Genre;
import com.example.secclient.entity.Publisher;
import com.example.secclient.service.entity.AuthorService;
import com.example.secclient.service.entity.BookService;
import com.example.secclient.service.entity.GenreService;
import com.example.secclient.service.entity.PublisherService;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class BookController {

    private final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final GenreService genreService = new GenreService();
    private final PublisherService publisherService = new PublisherService();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alert_bad = new Alert(Alert.AlertType.ERROR);


    boolean flag = true;

    @FXML
    private ComboBox<Author> comboBoxAuthor;

    @FXML
    private ComboBox<Genre> comboBoxGenre;

    @FXML
    private ComboBox<Publisher> comboBoxPublisher;

    @FXML
    private TextField textName;

    @FXML
    private TextField textYear;

    @FXML
    private Button buttonAdd;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        authorService.getAll();
        publisherService.getAll();
        genreService.getAll();

        comboBoxAuthor.setItems(authorService.getAuthors());
        comboBoxGenre.setItems(genreService.getGenres());
        comboBoxPublisher.setItems(publisherService.getPublishers());
    }

    @Setter
    @Getter
    private Optional<Book> book;

    public void start() {
        if(book.isPresent()) {
            textName.setText(book.get().getTitle());
            textYear.setText(book.get().getYear());

            comboBoxAuthor.setValue(book.get().getAuthor());
            comboBoxGenre.setValue(book.get().getGenre());
            comboBoxPublisher.setValue(book.get().getPublisher());
            buttonAdd.setText("Изменить");
        }
    }

    @FXML
    void addNewBook(ActionEvent event) {
        Book temp = new Book();
        temp.setTitle(textName.getText());
        temp.setYear(textYear.getText());
        String errorMessage = "";
        int iteError = 0;

        if ( !textYear.getText().isEmpty() & textYear.getText().matches("^(?:19|20)\\d{2}$")) {
            temp.setYear(textYear.getText());
        } else {
            errorMessage += "\nполе Год должно выглядеть так: 2015";
        }
        if ( !comboBoxAuthor.getSelectionModel().isEmpty()) {
            temp.setAuthor(comboBoxAuthor.getSelectionModel().getSelectedItem());
        } else{
            errorMessage += "\nполе Автор должно быть выбрано";
        }
        if ( !comboBoxGenre.getSelectionModel().isEmpty()) {
            temp.setGenre(comboBoxGenre.getSelectionModel().getSelectedItem());
        } else{
            errorMessage += "\nполе Жанр должно быть выбрано";
        }
        if ( !comboBoxPublisher.getSelectionModel().isEmpty()) {
            temp.setPublisher(comboBoxPublisher.getSelectionModel().getSelectedItem());
        } else{
            errorMessage += "\nполе Издание должно быть выбрано";
        }

        setBook(book = Optional.of(temp));
        if (errorMessage.isEmpty()) {
            alert.setTitle("Успешно");
            alert.setHeaderText("Данные добавленны");
            alert.showAndWait();
            dialogStage.close();
        } else {
            alert_bad.setTitle("Ошибка");
            alert_bad.setHeaderText("Ошибка ввода!");
            alert_bad.setContentText(errorMessage);
            alert_bad.showAndWait();
            iteError++;
        }
    }

    @FXML
    void changeBook(ActionEvent event) {
        buttonAdd.setText("Изменить");
        Book temp = new Book();
        temp.setTitle(textName.getText());
        temp.setYear(textYear.getText());
        temp.setAuthor(comboBoxAuthor.getSelectionModel().getSelectedItem());
        temp.setGenre(comboBoxGenre.getSelectionModel().getSelectedItem());
        temp.setPublisher(comboBoxPublisher.getSelectionModel().getSelectedItem());

        book = Optional.of(temp);
        setBook(book);

        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();
        dialogStage.close();
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        textName.clear();
        textYear.clear();
        comboBoxAuthor.valueProperty().set(null);
        comboBoxGenre.valueProperty().set(null);
        comboBoxPublisher.valueProperty().set(null);
    }
    public boolean addFlag() {
        return false;
    }
}
