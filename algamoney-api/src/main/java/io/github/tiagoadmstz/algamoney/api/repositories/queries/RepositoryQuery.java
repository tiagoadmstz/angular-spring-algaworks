package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.repositories.filters.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class RepositoryQuery<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<T> clazz;

    protected Page<?> executeQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Pageable pageable, Filter filter, Root<T> root) {
        Predicate[] predicates = createPredicates(filter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);
        addPaginationRegistrictions(typedQuery, pageable);

        return new PageImpl(typedQuery.getResultList(), pageable, total(filter));
    }

    protected abstract Predicate[] createPredicates(Filter filter, CriteriaBuilder criteriaBuilder, Root<T> root);

    protected void addPaginationRegistrictions(TypedQuery<?> typedQuery, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int first = pageNumber * pageSize;

        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(pageSize);
    }

    protected Long total(Filter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<T> root = query.from(clazz);

        Predicate[] predicates = createPredicates(filter, criteriaBuilder, root);
        query.where(predicates);

        query.select(criteriaBuilder.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

}
