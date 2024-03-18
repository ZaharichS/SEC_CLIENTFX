package com.example.secclient.controller;

import com.example.secclient.entity.Author;
import com.example.secclient.entity.City;
import com.example.secclient.service.entity.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CityController {

    private final CityService service = new CityService();
    private boolean addFlag = true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
        City city = new City();
        city.setTitle(textName.getText());
        if (addFlag) {
            service.add(city);
        } else {
            city.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.update(city, dataList.getSelectionModel().getSelectedItem());
        }
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();

        textName.clear();
        textName.clear();
        dataList.getItems().clear();
        initialize();
    }

    // Выбор города по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                addFlag = false;
                City tempCity = dataList.getSelectionModel().getSelectedItem();
                textName.setText(tempCity.getTitle());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        addFlag = true;
        textName.clear();
    }

    @FXML
    void deleteCity(ActionEvent event) {
        City tempCity = dataList.getSelectionModel().getSelectedItem();
        textName.setText(tempCity.getTitle());
        if (addFlag) {
            service.delete(tempCity);
            dataList.getSelectionModel().clearSelection();
        } else {
            tempCity.setId(dataList.getSelectionModel().getSelectedItem().getId());
            service.delete(tempCity);
        }
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные были удалены!");
        alert.showAndWait();

        textName.clear();
        dataList.getItems().clear();
        initialize();
    }

}

