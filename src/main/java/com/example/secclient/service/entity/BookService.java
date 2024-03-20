package com.example.secclient.service.entity;

import com.example.secclient.entity.Book;
import com.example.secclient.response.BaseResponse;
import com.example.secclient.response.DataResponse;
import com.example.secclient.response.ListResponse;
import com.example.secclient.service.ClientProperties;
import com.example.secclient.service.HttpService;
import com.example.secclient.service.JsonService;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class BookService {

    @Getter
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    /*
    Запоминаем тип данных DataResponse и ListResponse
     */
    private Type dataType = new TypeToken<DataResponse<Book>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Book>>() {}.getType();

    public void getAll() {
        ListResponse<Book> bookList = new ListResponse<>();
        bookList = json.getObject(httpService.get(client_property.getAllBook()), listType);
        if (bookList.isStatus()) {
            this.books.addAll(bookList.getData());
            this.books.forEach(System.out::println);
        } else {
            throw new RuntimeException(bookList.getStatus_text());
        }
    }

    public void add(Book book) {
        String tempData = httpService.post(client_property.getSaveBook(), json.getJson(book));
        DataResponse<Book> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.books.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Book book) {
        String tempData = httpService.put(client_property.getUpdateBook(), json.getJson(book));
        DataResponse<Book> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.books.remove(book);
            this.books.add(book);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Book book) {
        String tempData= httpService.delete(client_property.getDeleteBook(), book.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.books.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void findById(Book book) {
        String tempData = httpService.get(client_property.getFindByBookId() + book.getId());
        DataResponse<Book> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.books.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
