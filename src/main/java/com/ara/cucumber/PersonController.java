package com.ara.cucumber;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(
            PersonRepository personRepository) {this.personRepository = personRepository;}


    @GetMapping
    public List<PersonEntity> getAllPerson() {
        return personRepository.findAll();
    }

    @PostMapping
    public PersonEntity createPerson(@RequestBody PersonEntity person) {
        return personRepository.save(person);
    }
}
