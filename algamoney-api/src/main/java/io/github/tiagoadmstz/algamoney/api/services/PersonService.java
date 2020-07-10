package io.github.tiagoadmstz.algamoney.api.services;

import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person) {
        return findById(id, psn -> {
            BeanUtils.copyProperties(person, psn, "id");
            return personRepository.save(psn);
        });
    }

    public void updateActiveProperty(Long id, Boolean active) {
        findById(id, person -> {
            person.setActive(active);
            return personRepository.save(person);
        });
    }

    public Person findById(Long id, Function<Person, Person> function) {
        return personRepository.findById(id)
                .map(person -> function != null ? function.apply(person) : person)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

}
