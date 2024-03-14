package com.example.secclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 465);
        stage.setTitle("Your Book App");
        /*
        Устанавливаем иконку на родном toolbar
        Параметр который запрещает масштабировать окно
        */
        //stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("image/main_icon.png"))));
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}