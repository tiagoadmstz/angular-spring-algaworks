package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.events.CreateEvent;
import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Person> list(@RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer size) {
        if (page != null & size != null) {
            return personRepository.findAll(PageRequest.of(page, size)).getContent();
        }
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return personRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person createdPerson = personRepository.save(person);
        publisher.publishEvent(new CreateEvent(this, response, createdPerson.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

}
