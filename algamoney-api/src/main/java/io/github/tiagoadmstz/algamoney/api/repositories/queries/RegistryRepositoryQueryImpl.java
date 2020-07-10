package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Registry;
import io.github.tiagoadmstz.algamoney.api.models.Registry_;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.RegistryFilter;
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

public class RegistryRepositoryQueryImpl implements RegistryRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Registry> filter(RegistryFilter registryFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Registry> criteriaQuery = criteriaBuilder.createQuery(Registry.class);
        Root<Registry> root = criteriaQuery.from(Registry.class);

        Predicate[] predicates = createPredicates(registryFilter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Registry> typedQuery = entityManager.createQuery(criteriaQuery);
        addPaginationRegistrictions(typedQuery, pageable);

        return new PageImpl(typedQuery.getResultList(), pageable, total(registryFilter));
    }

    private Predicate[] createPredicates(RegistryFilter registryFilter, CriteriaBuilder criteriaBuilder, Root<Registry> root) {
        List<Predicate> predicates = new ArrayList();

        if (!StringUtils.isEmpty(registryFilter.getDescription())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(Registry_.description)), "%" + registryFilter.getDescription() + "%"
            ));
        }
        if (registryFilter.getDueDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get(Registry_.dueDate), registryFilter.getDueDateFrom()
            ));
        }
        if (registryFilter.getDueDateUntil() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get(Registry_.dueDate), registryFilter.getDueDateUntil()
            ));
        }

        return predicates.stream().toArray(Predicate[]::new);
    }


    private void addPaginationRegistrictions(TypedQuery<Registry> typedQuery, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int first = pageNumber * pageSize;

        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(pageSize);
    }

    private Long total(RegistryFilter registryFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Registry> root = query.from(Registry.class);

        Predicate[] predicates = createPredicates(registryFilter, criteriaBuilder, root);
        query.where(predicates);

        query.select(criteriaBuilder.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

}
