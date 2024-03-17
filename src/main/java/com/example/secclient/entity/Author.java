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
public class Author {
    private Long id;
    private String lastname;
    private String name;
    private String surname;
    private List<Book> books;

    @Override
    public String toString() {
        return surname + " " +  name + " " + lastname;
    }
}
