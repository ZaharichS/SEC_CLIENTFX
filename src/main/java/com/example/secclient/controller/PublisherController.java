package com.example.secclient.controller;

import com.example.secclient.entity.City;
import com.example.secclient.entity.Genre;
import com.example.secclient.entity.Publisher;
import com.example.secclient.service.entity.CityService;
import com.example.secclient.service.entity.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PublisherController {

    private final PublisherService servicePublisher = new PublisherService();
    private final CityService cityService = new CityService();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private boolean flag = true;

    @FXML
    private ComboBox<City> comboBoxCity1;
    @FXML
    private ListView<Publisher> dataList;

    @FXML
    private TextField textName;

    @FXML
    private Button buttonAdd;

    @FXML
    private void initialize() {
        cityService.getAll();
        servicePublisher.getAll();

        dataList.setItems(servicePublisher.getPublishers());
        comboBoxCity1.setItems(cityService.getCities());
    }

    @FXML
    void addNewPublisher(ActionEvent event) {
        Publisher publisher = new Publisher();
        publisher.setTitle(textName.getText());
        publisher.setCity(comboBoxCity1.getSelectionModel().getSelectedItem());

        if (flag) {
            servicePublisher.add(publisher);
        } else {
            publisher.setId(dataList.getSelectionModel().getSelectedItem().getId());
            servicePublisher.update(publisher, dataList.getSelectionModel().getSelectedItem());
        }
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные добавленны");
        alert.showAndWait();

        textName.clear();
        comboBoxCity1.valueProperty().set(null);
        dataList.getItems().clear();
        initialize();
    }

    // Выбор издания по двойному клику
    @FXML
    void onMouseClickDataList(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2) {
                buttonAdd.setText("Изменить");
                flag = false;
                Publisher tempPublisher = dataList.getSelectionModel().getSelectedItem();
                textName.setText(tempPublisher.getTitle());
                comboBoxCity1.getSelectionModel().select(tempPublisher.getCity());
            }
        }
    }

    @FXML
    void cancelEvent(ActionEvent event) {
        buttonAdd.setText("Добавить");
        flag = true;
        textName.clear();
        comboBoxCity1.valueProperty().set(null);
    }

    @FXML
    void deletePublisher(ActionEvent event) {
        servicePublisher.delete(dataList.getSelectionModel().getSelectedItem());
        alert.setTitle("Успешно");
        alert.setHeaderText("Данные были удалены!");
        alert.showAndWait();

        textName.clear();
        comboBoxCity1.valueProperty().set(null);
        dataList.getItems().clear();
        initialize();
    }

}
