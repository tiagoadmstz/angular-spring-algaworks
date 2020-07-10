package io.github.tiagoadmstz.algamoney.api.services;

import io.github.tiagoadmstz.algamoney.api.models.Registry;
import io.github.tiagoadmstz.algamoney.api.repositories.RegistryRepository;
import io.github.tiagoadmstz.algamoney.api.services.exceptions.NonexistentPersonOrInactivePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {

    @Autowired
    private RegistryRepository registryRepository;
    @Autowired
    private PersonService personService;

    public Registry save(Registry registry) {
        personService.findById(registry.getPerson().getId(), person -> {
            if (person.isInactive()) throw new NonexistentPersonOrInactivePersonException();
            return person;
        });
        return registryRepository.save(registry);
    }

}
