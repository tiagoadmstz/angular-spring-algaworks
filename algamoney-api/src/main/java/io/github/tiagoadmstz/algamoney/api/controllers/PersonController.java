package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.events.CreateEvent;
import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.repositories.PersonRepository;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.PersonFilter;
import io.github.tiagoadmstz.algamoney.api.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public Page<Person> search(PersonFilter personFilter, Pageable pageable) {
        return personRepository.filter(personFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return personRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person createdPerson = personRepository.save(person);
        publisher.publishEvent(new CreateEvent(this, response, createdPerson.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        return ResponseEntity.ok(personService.update(id, person));
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active) {
        personService.updateActiveProperty(id, active);
    }

}
