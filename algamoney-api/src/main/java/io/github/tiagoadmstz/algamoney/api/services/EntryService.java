package io.github.tiagoadmstz.algamoney.api.services;

import io.github.tiagoadmstz.algamoney.api.models.Entry;
import io.github.tiagoadmstz.algamoney.api.repositories.EntryRepository;
import io.github.tiagoadmstz.algamoney.api.services.exceptions.NonexistentPersonOrInactivePersonException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EntryService {

    private final EntryRepository entryRepository;
    private final PersonService personService;

    public Entry save(Entry entry) {
        personService.findById(entry.getPerson().getId(), person -> {
            if (person.isInactive()) throw new NonexistentPersonOrInactivePersonException();
            return person;
        });
        return entryRepository.save(entry);
    }

}
