package com.example.fds2project.bdd;

import com.example.fds2project.domain.MovieList;
import com.example.fds2project.infrastructure.MovieListRepository;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieListSteps {

    @Autowired
    private MovieListRepository repository;

    private MovieList movieList;
    private MovieList savedMovieList;

    @Given("a user wants to create a movie list named {string}")
    public void a_user_wants_to_create_a_movie_list_named(String name) {
        movieList = new MovieList();
        movieList.setName(name);
    }

    @When("the user sets the privacy to {string}")
    public void the_user_sets_the_privacy_to(String privacy) {
        movieList.setPrivacySetting(privacy);
    }

    @When("saves the movie list")
    public void saves_the_movie_list() {
        savedMovieList = repository.save(movieList);
    }

    @Then("the movie list should be created successfully")
    public void the_movie_list_should_be_created_successfully() {
        Assert.assertNotNull(savedMovieList);
    }

    @Then("assigned an ID")
    public void assigned_an_id() {
        Assert.assertNotNull(savedMovieList.getId());
    }
}
