package com.example.secclient.service.entity;

import com.example.secclient.entity.City;
import com.example.secclient.entity.Genre;
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
import java.util.List;

public class GenreService {

    @Getter
    private ObservableList<Genre> genres = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    /*
    Запоминаем тип данных DataResponse и ListResponse
     */
    private Type dataType = new TypeToken<DataResponse<Genre>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<Genre>>() {}.getType();

    public void getAll() {
        ListResponse<Genre> genreList = new ListResponse<>();
        genreList = json.getObject(httpService.get(client_property.getAllGenre()), listType);
        if (genreList.isStatus()) {
            this.genres.addAll(genreList.getData());
            this.genres.forEach(System.out::println);
        } else {
            throw new RuntimeException(genreList.getStatus_text());
        }
    }

    public void add(Genre genre) {
        String tempData = httpService.post(client_property.getSaveGenre(), json.getJson(genre));
        DataResponse<Genre> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.genres.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Genre genre) {
        String tempData = httpService.put(client_property.getUpdateGenre(), json.getJson(genre));
        DataResponse<Genre> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.genres.remove(genre);
            this.genres.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Genre genre) {
        String tempData= httpService.delete(client_property.getDeleteGenre(), genre.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.genres.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void findById(Genre genre) {
        String tempData = httpService.get(client_property.getFindByGenreId() + genre.getId());
        DataResponse<Genre> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.genres.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
