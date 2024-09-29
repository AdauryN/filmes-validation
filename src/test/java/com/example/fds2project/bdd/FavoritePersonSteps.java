package com.example.fds2project.bdd;

import com.example.fds2project.application.FavoritePersonService;
import com.example.fds2project.application.UserService;
import com.example.fds2project.domain.FavoritePerson;
import com.example.fds2project.domain.Person;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.PersonRepository;
import com.example.fds2project.infrastructure.UserRepository;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FavoritePersonSteps {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoritePersonService favoritePersonService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private String responseMessage;
    private Exception exception;

    @Given("que o usuário está autenticado como {string}")
    public void que_o_usuario_está_autenticado_como(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("password123");
        user.setEmail(username + "@example.com");
        try {
            testUser = userService.registerUser(user);
        } catch (Exception e) {
            testUser = userRepository.findByUsername(username);
        }
    }

    @Given("que o ator ou diretor {string} com ID {long} existe no sistema")
    public void que_o_ator_ou_diretor_com_id_existe_no_sistema(String personName, Long personId) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            person = new Person();
            person.setId(personId);
            person.setName(personName);
            person.setRole("actor"); // or "director" as appropriate
            personRepository.save(person);
        }
    }

    @When("o usuário favorita o ator ou diretor com ID {long}")
    public void o_usuario_favorita_o_ator_ou_diretor_com_id(Long personId) {
        try {
            responseMessage = favoritePersonService.favoritePerson(testUser.getUsername(), personId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("o sistema deve exibir a mensagem {string}")
    public void o_sistema_deve_exibir_a_mensagem(String expectedMessage) {
        if (exception != null) {
            Assert.assertEquals(expectedMessage, exception.getMessage());
        } else {
            Assert.assertEquals(expectedMessage, responseMessage);
        }
    }

    @Then("o nome {string} deve aparecer na lista de pessoas favoritas do usuário")
    public void o_nome_deve_aparecer_na_lista_de_pessoas_favoritas_do_usuário(String personName) {
        List<FavoritePerson> favoritePersons = favoritePersonService.getFavoritePersons(testUser.getUsername());
        boolean found = favoritePersons.stream()
                .anyMatch(fav -> fav.getPerson().getName().equals(personName));
        Assert.assertTrue(found);
    }
}
