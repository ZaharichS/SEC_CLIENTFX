package com.example.secclient;

import com.example.secclient.entity.Book;
import com.example.secclient.service.HttpService;
import com.example.secclient.service.entity.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private Button add_button;
    BookService service = new BookService();

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
        service.getAll();

        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookGenre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        bookYear.setCellValueFactory(new PropertyValueFactory<Book, String>("year"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookPublish.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        bookTable.setItems(service.getBooks());
    }

    @FXML
    void addBookAction(ActionEvent event) {
        //System.out.println(service.get("http://localhost:28245/api/v1/book/all"));
    }

    @FXML
    void addOrChangeAuthorAction(ActionEvent event) {

    }

    @FXML
    void addOrChangeCityAction(ActionEvent event) {

    }

    @FXML
    void addOrChangeGenreAction(ActionEvent event) {

    }

    @FXML
    void addOrChangePublisherAction(ActionEvent event) {

    }

    @FXML
    void changeBookAction(ActionEvent event) {

    }

    @FXML
    void deleteBookAction(ActionEvent event) {

    }

}