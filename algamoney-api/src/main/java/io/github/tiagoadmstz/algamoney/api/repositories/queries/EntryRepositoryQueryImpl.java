package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Category_;
import io.github.tiagoadmstz.algamoney.api.models.Entry;
import io.github.tiagoadmstz.algamoney.api.models.Entry_;
import io.github.tiagoadmstz.algamoney.api.models.Person_;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.EntryFilter;
import io.github.tiagoadmstz.algamoney.api.repositories.projections.SummaryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryQueryImpl implements EntryRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteriaQuery = criteriaBuilder.createQuery(Entry.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        return (Page<Entry>) executeQuery(criteriaBuilder, criteriaQuery, pageable, entryFilter, root);
    }

    @Override
    public Page<SummaryEntry> summarize(EntryFilter entryFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SummaryEntry> criteriaQuery = criteriaBuilder.createQuery(SummaryEntry.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        criteriaQuery.select(criteriaBuilder.construct(SummaryEntry.class,
                root.get(Entry_.id), root.get(Entry_.description),
                root.get(Entry_.dueDate), root.get(Entry_.payday),
                root.get(Entry_.value), root.get(Entry_.entryType),
                root.get(Entry_.category).get(Category_.name),
                root.get(Entry_.person).get(Person_.name)
        ));

        return (Page<SummaryEntry>) executeQuery(criteriaBuilder, criteriaQuery, pageable, entryFilter, root);
    }

    private Page<?> executeQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Pageable pageable, EntryFilter entryFilter, Root<Entry> root) {
        Predicate[] predicates = createPredicates(entryFilter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);
        addPaginationRegistrictions(typedQuery, pageable);

        return new PageImpl(typedQuery.getResultList(), pageable, total(entryFilter));
    }

    private Predicate[] createPredicates(EntryFilter entryFilter, CriteriaBuilder criteriaBuilder, Root<Entry> root) {
        List<Predicate> predicates = new ArrayList();

        if (!StringUtils.isEmpty(entryFilter.getDescription())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(Entry_.description)), "%" + entryFilter.getDescription() + "%"
            ));
        }
        if (entryFilter.getDueDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get(Entry_.dueDate), entryFilter.getDueDateFrom()
            ));
        }
        if (entryFilter.getDueDateUntil() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get(Entry_.dueDate), entryFilter.getDueDateUntil()
            ));
        }

        return predicates.stream().toArray(Predicate[]::new);
    }

    private void addPaginationRegistrictions(TypedQuery<?> typedQuery, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int first = pageNumber * pageSize;

        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(pageSize);
    }

    private Long total(EntryFilter entryFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Entry> root = query.from(Entry.class);

        Predicate[] predicates = createPredicates(entryFilter, criteriaBuilder, root);
        query.where(predicates);

        query.select(criteriaBuilder.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

}
