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
public class City {
    private Long id;
    private String title;

    private List<Publisher> publisher;

    @Override
    public String toString() {
        return title;
    }
}
