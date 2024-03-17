package com.example.secclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    private Long id;
    private String title;

    private City city;

    private List<Book> books;

    @Override
    public String toString() {
        return title + "( г. " + city + " )";
    }
}
