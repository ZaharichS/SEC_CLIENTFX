package com.example.secclient;

import com.example.secclient.controller.BookController;
import com.example.secclient.entity.Book;
import com.example.secclient.entity.Genre;
import com.example.secclient.service.HttpService;
import com.example.secclient.service.entity.BookService;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class MainController {

    @FXML
    private Button add_button;
    BookService service = new BookService();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private boolean addFlag = true;

    @FXML
    private TableColumn<Book, String> bookAuthor;

    @FXML
    private TableColumn<Book, String> bookGenre;

    @FXML
    private TableColumn<Book, String> bookYear;

    @FXML
    private TableColumn<Book, String> bookPublish;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookTitle;

    @FXML
    private void initialize() {
        try {
            service.getAll();
        } catch (Exception e ) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Отсутствует подключение к серверу ");
            alert.setContentText("Обратитесь в тех.поддержку.....");
            alert.showAndWait();
        }

        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookGenre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        bookYear.setCellValueFactory(new PropertyValueFactory<Book, String>("year"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookPublish.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));

        bookTable.setItems(service.getBooks());
    }

    @FXML
    void callRefreshFunc(ActionEvent event) {
        bookTable.getItems().clear();
        initialize();
    }

    @FXML
    void callBookModal(ActionEvent event) {
        Optional<Book> book = Optional.empty();
        MainApplication.showBookModal(book);
        bookTable.getItems().clear();
        initialize();
    }

    @FXML
    void callAuthorModal(ActionEvent event) {
        MainApplication.showAuthorModal();
    }

    @FXML
    void callCityModal(ActionEvent event) {
        MainApplication.showCityModal();
    }

    @FXML
    void callGenreModal(ActionEvent event) {
        MainApplication.showGenreModal();
    }

    @FXML
    void callPublisherModal(ActionEvent event) {
        MainApplication.showPublisherModal();
    }

    @FXML
    void changeBookAction(ActionEvent event) {
        Optional<Book> book = Optional.ofNullable(bookTable.getSelectionModel().getSelectedItem());
        if (bookTable.getSelectionModel().getSelectedItem() != null) {
            MainApplication.showBookModal(book);
            bookTable.getItems().clear();
            initialize();
        } else {
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранная книга ");
            alert.setContentText("Выберите книгу в таблице");
            alert.showAndWait();
        }
    }

    private Optional<Book> book;
    public void setBook(Optional<Book> book) {
        this.book = book;
        if (book.isPresent()) {
            if (book.get().getId() != null) {
                service.update(book.get());
            } else {
                // ДОПИСАТЬ
                try {
                    service.delete(bookTable.getSelectionModel().getSelectedItem());
                    service.add(book.get());
                } catch (Exception e) {
                    service.add(book.get());
                }
            }
        }
    }

    @FXML
    void deleteBookAction(ActionEvent event) {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            service.delete(selectedBook);

            alert.setTitle("Успешно");
            alert.setHeaderText("Данные удалены");
            alert.showAndWait();

            bookTable.getItems().clear();
            initialize();
        } else {
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранная книга ");
            alert.setContentText("Выберите книгу в таблице");
            alert.showAndWait();
        }
    }

}