package io.github.tiagoadmstz.algamoney.api.services;

import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person) {
        return personRepository.findById(id).map(psn -> {
            BeanUtils.copyProperties(person, psn, "id");
            return personRepository.save(psn);
        }).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }


    public void updateActiveProperty(Long id, Boolean active) {
        personRepository.findById(id).map(person -> {
            person.setActive(active);
            return personRepository.save(person);
        }).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
