package com.example.secclient;

import com.example.secclient.controller.BookController;
import com.example.secclient.entity.Book;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("image/icons/png/main_icon.png"))));
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void showAuthorModal()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("entity/add-author-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация автора");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.setResizable(false);
            dialogStage.getIcons().add(
                    new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/icons/png/main_icon.png"))));

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showBookModal(Optional<Book> book)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("entity/add-book-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            BookController controller = loader.getController();
            controller.setBook(book);
            controller.start();

            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/icons/png/main_icon.png"))));

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void showCityModal()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("entity/add-city-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация города");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/icons/png/main_icon.png"))));

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showGenreModal()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("entity/add-genre-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация жанра");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/icons/png/main_icon.png"))));

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showPublisherModal()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("entity/add-publisher-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Регистрация издания");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("image/icons/png/main_icon.png"))));

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}