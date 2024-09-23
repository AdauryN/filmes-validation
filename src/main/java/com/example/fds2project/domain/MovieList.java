package com.example.fds2project.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieList {
    private Long id;
    private String name;
    private String privacySetting;
    private List<String> movies;

    public MovieList() {
        movies = new ArrayList<>();
    }
}
