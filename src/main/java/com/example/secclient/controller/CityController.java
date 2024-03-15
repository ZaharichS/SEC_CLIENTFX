package com.example.secclient.controller;

import com.example.secclient.entity.City;
import com.example.secclient.service.entity.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CityController {

    private final CityService service = new CityService();

    @FXML
    private ListView<City> dataList;

    @FXML
    private TextField textName;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getCities());
    }

    @FXML
    void addNewCity(ActionEvent event) {

    }

    @FXML
    void cancelEvent(ActionEvent event) {

    }

    @FXML
    void deleteCity(ActionEvent event) {

    }

}

