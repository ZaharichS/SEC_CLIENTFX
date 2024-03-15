package com.example.secclient.service;

import com.example.secclient.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private final Properties properties = new Properties();

    private String allBook;
    private String findByBookId;
    private String saveBook;
    private String updateBook;
    private String findByBookTitle;

    private String allAuthor;
    private String findByAuthorId;
    private String saveAuthor;
    private String updateAuthor;
    private String findByAuthorTitle;

    private String allCity;
    private String findByCityId;
    private String saveCity;
    private String updateCity;

    private String allGenre;
    private String findByGenreId;
    private String saveGenre;
    private String updateGenre;

    private String allPublisher;
    private String findByPublisherId;
    private String savePublisher;
    private String updatePublisher;

    private String deleteBook;
    private String deleteAuthor;
    private String deleteCity;
    private String deleteGenre;
    private String deletePublisher;

    public ClientProperties() {
        try (InputStream inputStream = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(inputStream);

            properties.load(inputStream);
            allBook = properties.getProperty("book.getAll");
            findByBookId = properties.getProperty("book.findById");
            deleteBook = properties.getProperty("book.delById");
            saveBook = properties.getProperty("book.save");
            updateBook = properties.getProperty("book.update");
            findByBookTitle = properties.getProperty("book.findByTitle");

            allAuthor = properties.getProperty("author.getAll");
            findByAuthorId = properties.getProperty("author.findById");
            deleteAuthor = properties.getProperty("author.delById");
            saveAuthor = properties.getProperty("author.save");
            updateAuthor = properties.getProperty("author.update");

            allCity = properties.getProperty("city.getAll");
            findByCityId = properties.getProperty("city.findById");
            deleteCity = properties.getProperty("city.delById");
            saveCity = properties.getProperty("city.save");
            updateCity = properties.getProperty("city.update");

            allGenre = properties.getProperty("genre.getAll");
            findByGenreId = properties.getProperty("genre.findById");
            deleteGenre = properties.getProperty("genre.delById");
            saveGenre = properties.getProperty("genre.save");
            updateGenre = properties.getProperty("genre.update");

            allPublisher = properties.getProperty("publisher.getAll");
            findByPublisherId = properties.getProperty("publisher.findById");
            deletePublisher = properties.getProperty("publisher.delById");
            savePublisher = properties.getProperty("publisher.save");
            updatePublisher = properties.getProperty("publisher.update");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
