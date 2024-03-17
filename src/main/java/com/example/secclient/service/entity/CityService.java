package com.example.secclient.service.entity;

import com.example.secclient.entity.Author;
import com.example.secclient.entity.City;
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

public class CityService {

    @Getter
    private ObservableList<City> cities = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    JsonService json = new JsonService();
    ClientProperties client_property = new ClientProperties();

    /*
    Запоминаем тип данных DataResponse и ListResponse
     */
    private Type dataType = new TypeToken<DataResponse<City>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<City>>() {}.getType();

    public void getAll() {
        ListResponse<City> cityList = new ListResponse<>();
        cityList = json.getObject(httpService.get(client_property.getAllCity()), listType);
        if (cityList.isStatus()) {
            this.cities.addAll(cityList.getData());
            sortByCity();
        } else {
            throw new RuntimeException(cityList.getStatus_text());
        }
    }

    public void add(City city) {
        String tempData = httpService.post(client_property.getSaveCity(), json.getJson(city));
        DataResponse<City> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.cities.add(response.getData());
            sortByCity();
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void update(City city_main, City city_new) {
        String tempData = httpService.put(client_property.getUpdateCity(), json.getJson(city_new));
        DataResponse<City> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.cities.remove(city_main);
            this.cities.add(city_new);
            sortByCity();
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void delete(City city) {
        String tempData= httpService.delete(client_property.getDeleteCity(), city.getId());
        BaseResponse response = json.getObject(tempData, BaseResponse.class);
        if (response.isStatus()) {
            this.cities.remove(tempData);
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    public void findById(City city) {
        String tempData = httpService.get(client_property.getFindByCityId() + city.getId());
        DataResponse<City> response = json.getObject(tempData, dataType);
        if (response.isStatus()) {
            this.cities.add(response.getData());
        } else {
            throw new RuntimeException(response.getStatus_text());
        }
    }

    // Сортировка по городу
    private void sortByCity() {
        cities.sort(Comparator.comparing(City::getTitle));
    }
}
