package com.ara.cucumber.stepdefinitions;

import com.ara.cucumber.PersonEntity;
import com.ara.cucumber.PersonRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonManagementSteps {

    @Autowired
    private PersonRepository personRepository;

    @Given("there are no person")
    public void thereAreNoPerson() {
        personRepository.deleteAll();
    }

    @When("I create a person with name {string} and with age {int}")
    public void iCreateAPersonWithNameAndWithAge(String name, int age) throws Exception {
        PersonEntity person = new PersonEntity();
        person.setName(name);
        person.setAge(age);

        personRepository.save(person);
    }

    @Then("the person list should have {int} person with the name {string}")
    public void thePersonListShouldHavePersonWithTheName(int count, String name) {
        List<PersonEntity> allPerson = personRepository.findAll();

        Assertions.assertEquals(count, allPerson.size());

        PersonEntity person = allPerson.get(0);

        Assertions.assertEquals(name, person.getName());
    }

}
