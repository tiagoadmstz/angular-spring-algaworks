package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Person;
import io.github.tiagoadmstz.algamoney.api.models.Person_;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.Filter;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryQueryImpl extends RepositoryQuery<Person> implements PersonRepositoryQuery {

    public PersonRepositoryQueryImpl() {
        this.clazz = Person.class;
    }

    @Override
    public Page<Person> filter(PersonFilter personFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        return (Page<Person>) executeQuery(criteriaBuilder, criteriaQuery, pageable, personFilter, root);
    }

    private Page<?> executeQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Pageable pageable, PersonFilter personFilter, Root<Person> root) {
        Predicate[] predicates = createPredicates(personFilter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);
        addPaginationRegistrictions(typedQuery, pageable);

        return new PageImpl(typedQuery.getResultList(), pageable, total(personFilter));
    }

    @Override
    protected Predicate[] createPredicates(Filter filter, CriteriaBuilder criteriaBuilder, Root<Person> root) {
        List<Predicate> predicates = new ArrayList();
        PersonFilter personFilter = (PersonFilter) filter;
        if (!StringUtils.isEmpty(personFilter.getName())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(Person_.name)), "%" + personFilter.getName() + "%"
            ));
        }
        return predicates.stream().toArray(Predicate[]::new);
    }

}
