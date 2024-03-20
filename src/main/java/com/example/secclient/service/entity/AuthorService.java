package com.example.secclient.service.entity;

import com.example.secclient.entity.Author;
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
import java.util.Comparator;

public class AuthorService {

    @Getter
    private ObservableList<Author> authors = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    /*
    Запоминаем тип данных DataResponse и ListResponse
     */
    private Type dataType = new TypeToken<DataResponse<Author>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Author>>() {}.getType();

    public void getAll() {
        ListResponse<Author> authorList = new ListResponse<>();
        authorList = json.getObject(httpService.get(client_property.getAllAuthor()), listType);
        if (authorList.isStatus()) {
            this.authors.addAll(authorList.getData());
            sortBySurname();
        } else {
            throw new RuntimeException(authorList.getStatus_text());
        }
    }

    public void add(Author author) {
        String tempData = httpService.post(client_property.getSaveAuthor(), json.getJson(author));
        DataResponse<Author> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.authors.add(response.getData());
            sortBySurname();
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Author author_new, Author author_older) {
        String tempData = httpService.put(client_property.getUpdateAuthor(), json.getJson(author_new));
        DataResponse<Author> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.authors.remove(author_older);
            this.authors.add(author_new);
            sortBySurname();
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Author author) {
        String tempData= httpService.delete(client_property.getDeleteAuthor(), author.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.authors.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void findById(Author author) {
        String tempData = httpService.get(client_property.getFindByAuthorId() + author.getId());
        DataResponse<Author> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.authors.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    // Сортировка по фамилии
    private void sortBySurname() {
        authors.sort(Comparator.comparing(Author::getSurname));
    }
}
