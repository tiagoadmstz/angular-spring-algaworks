package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Category_;
import io.github.tiagoadmstz.algamoney.api.models.Entry;
import io.github.tiagoadmstz.algamoney.api.models.Entry_;
import io.github.tiagoadmstz.algamoney.api.models.Person_;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.EntryFilter;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.Filter;
import io.github.tiagoadmstz.algamoney.api.repositories.projections.SummaryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryQueryImpl extends RepositoryQuery<Entry> implements EntryRepositoryQuery {

    public EntryRepositoryQueryImpl() {
        this.clazz = Entry.class;
    }

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

    @Override
    protected Predicate[] createPredicates(Filter filter, CriteriaBuilder criteriaBuilder, Root<Entry> root) {
        List<Predicate> predicates = new ArrayList();
        EntryFilter entryFilter = (EntryFilter) filter;
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

}
