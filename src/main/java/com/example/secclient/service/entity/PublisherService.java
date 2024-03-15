package com.example.secclient.service.entity;

import com.example.secclient.entity.Genre;
import com.example.secclient.entity.Publisher;
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

public class PublisherService {

    @Getter
    private ObservableList<Publisher> publishers = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    /*
    Запоминаем тип данных DataResponse и ListResponse
     */
    private Type dataType = new TypeToken<DataResponse<Publisher>>() {}.getType();
    private Type listType = new TypeToken<DataResponse<Publisher>>() {}.getType();

    public void getAll() {
        ListResponse<Publisher> publisherList = new ListResponse<>();
        publisherList = json.getObject(httpService.get(client_property.getAllPublisher()), listType);
        if (publisherList.isStatus()) {
            this.publishers.addAll(publisherList.getData());
            this.publishers.forEach(System.out::println);
        } else {
            throw new RuntimeException(publisherList.getStatus_text());
        }
    }

    public void add(Publisher publisher) {
        String tempData = httpService.post(client_property.getSavePublisher(), json.getJson(publisher));
        DataResponse<Publisher> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.publishers.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(Publisher publisher) {
        String tempData = httpService.put(client_property.getUpdatePublisher(), json.getJson(publisher));
        DataResponse<Publisher> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.publishers.remove(publisher);
            this.publishers.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(Publisher publisher) {
        String tempData= httpService.delete(client_property.getDeletePublisher(), publisher.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.publishers.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void findById(Publisher publisher) {
        String tempData = httpService.get(client_property.getFindByPublisherId() + publisher.getId());
        DataResponse<Publisher> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.publishers.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }
}
