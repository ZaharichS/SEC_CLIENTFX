package com.example.secclient.controller;

import com.example.secclient.entity.Publisher;
import com.example.secclient.service.entity.PublisherService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PublisherController {

    private final PublisherService service = new PublisherService();

    @FXML
    private ComboBox<?> ComboBoxCity1;
    @FXML
    private ListView<Publisher> dataList;

    @FXML
    private TextField textName;

    @FXML
    private void initialize() {
        service.getAll();
        dataList.setItems(service.getPublishers());
    }

    @FXML
    void addNewPublisher(ActionEvent event) {

    }

    @FXML
    void cancelEvent(ActionEvent event) {

    }

    @FXML
    void deletePublisher(ActionEvent event) {

    }

}
