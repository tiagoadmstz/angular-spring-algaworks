package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonRepositoryQuery {

    Page<Person> filter(PersonFilter personFilter, Pageable pageable);

}
