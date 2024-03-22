package com.example.secclient.controller;

import com.example.secclient.entity.Author;
import com.example.secclient.entity.City;
import com.example.secclient.service.entity.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CityController {

    private final CityService service = new CityService();
    private boolean addFlag = true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alert_bad = new Alert(Alert.AlertType.ERROR);

    @FXML
    private ListView<City> dataList;

    @FXML
    private TextField textName;

    @FXML
    private Button buttonAdd;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getCities());
    }

    @FXML
    void addNewCity(ActionEvent event) {
        City city = new City();
        String errorMesage = "";

        if ( !textName.getText().isEmpty() & textName.getText().matches("[А-Я][а-я]{1,20}")) {
            city.setTitle(textName.getText());
        } else {
            errorMesage += "\nполе Город должно выглядеть так: Москва";
        }
        if (addFlag) {
            try {
                service.add(city);
                alert.setTitle("Успешно");
                alert.setHeaderText("Данные добавленны");
                alert.showAndWait();

                textName.clear();
                textName.clear();
                dataList.getItems().clear();
                initialize();
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        } else {
            city.setId(dataList.getSelectionModel().getSelectedItem().getId());
            try {
                service.update(city, dataList.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        }
    }

    // Выбор города по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                buttonAdd.setText("Изменить");
                addFlag = false;
                City tempCity = dataList.getSelectionModel().getSelectedItem();
                textName.setText(tempCity.getTitle());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        buttonAdd.setText("Добавить");
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

