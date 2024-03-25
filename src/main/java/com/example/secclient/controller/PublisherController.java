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
import javafx.stage.Stage;

public class PublisherController {

    private final PublisherService servicePublisher = new PublisherService();
    private final CityService cityService = new CityService();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alert_bad = new Alert(Alert.AlertType.ERROR);

    private boolean flag = true;

    @FXML
    private ComboBox<City> comboBoxCity1;
    @FXML
    private ListView<Publisher> dataList;

    @FXML
    private TextField textName;

    @FXML
    private Button buttonAdd;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        try {
            cityService.getAll();
            servicePublisher.getAll();
        } catch (Exception e ) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Отсутствует подключение к серверу ");
            alert.setContentText("Обратитесь в тех.поддержку.....");
            alert.showAndWait();
            dialogStage.close();
        }

        dataList.setItems(servicePublisher.getPublishers());
        comboBoxCity1.setItems(cityService.getCities());
    }

    @FXML
    void addNewPublisher(ActionEvent event) {
        Publisher publisher = new Publisher();
        String errorMesage = "";

        if ( !textName.getText().isEmpty() & textName.getText().matches("[А-Я][а-я]{1,20}")) {
            publisher.setTitle(textName.getText());
        } else  {
            errorMesage += "\nполе Издание должно выглядеть так: Издание";
        }
        if ( !comboBoxCity1.getSelectionModel().isEmpty()) {
            publisher.setCity(comboBoxCity1.getSelectionModel().getSelectedItem());
        } else{
            errorMesage += "\nполе Город должно быть выбрано";
        }

        if (flag) {
            try {
                servicePublisher.add(publisher);
                alert.setTitle("Успешно");
                alert.setHeaderText("Данные добавленны");
                alert.showAndWait();

                textName.clear();
                comboBoxCity1.valueProperty().set(null);
                dataList.getItems().clear();
                initialize();
            } catch (Exception e) {
                alert_bad.setTitle("Ошибка");
                alert_bad.setHeaderText("Ошибка ввода!");
                alert_bad.setContentText(errorMesage);
                alert_bad.showAndWait();
            }
        } else {
            publisher.setId(dataList.getSelectionModel().getSelectedItem().getId());
            try {
                servicePublisher.update(publisher, dataList.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                if (!errorMesage.isEmpty()) {
                    alert_bad.setTitle("Ошибка");
                    alert_bad.setHeaderText("Ошибка ввода!");
                    alert_bad.setContentText(errorMesage);
                    alert_bad.showAndWait();
                } else {
                    alert_bad.setTitle("Ошибка");
                    alert_bad.setHeaderText("Ошибка подключения!");
                    alert_bad.setContentText("Подключитесь к серверу...");
                    alert_bad.showAndWait();
                }
            }
        }

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
