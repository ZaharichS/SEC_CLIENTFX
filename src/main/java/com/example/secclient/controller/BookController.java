package com.example.secclient.controller;

import com.example.secclient.MainController;
import com.example.secclient.entity.Author;
import com.example.secclient.entity.Book;
import com.example.secclient.entity.Genre;
import com.example.secclient.entity.Publisher;
import com.example.secclient.service.entity.AuthorService;
import com.example.secclient.service.entity.BookService;
import com.example.secclient.service.entity.GenreService;
import com.example.secclient.service.entity.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class BookController {

    private final BookService bookService = new BookService();
    private final AuthorService authorService = new AuthorService();
    private final GenreService genreService = new GenreService();
    private final PublisherService publisherService = new PublisherService();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
        }
    }

    @FXML
    void addNewBook(ActionEvent event) {
        Book temp = new Book();
        temp.setTitle(textName.getText());
        temp.setYear(textYear.getText());
        temp.setAuthor(comboBoxAuthor.getSelectionModel().getSelectedItem());
        temp.setGenre(comboBoxGenre.getSelectionModel().getSelectedItem());
        temp.setPublisher(comboBoxPublisher.getSelectionModel().getSelectedItem());

        book = Optional.of(temp);

        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();

    }

    @FXML
    void changeBook(ActionEvent event) {
        Book temp = new Book();
        temp.setTitle(textName.getText());
        temp.setYear(textYear.getText());
        temp.setAuthor(comboBoxAuthor.getSelectionModel().getSelectedItem());
        temp.setGenre(comboBoxGenre.getSelectionModel().getSelectedItem());
        temp.setPublisher(comboBoxPublisher.getSelectionModel().getSelectedItem());

        book = Optional.of(temp);
        bookService.update(temp);

        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        textName.clear();
        textYear.clear();
        comboBoxAuthor.valueProperty().set(null);
        comboBoxGenre.valueProperty().set(null);
        comboBoxPublisher.valueProperty().set(null);
    }
}
