package io.github.tiagoadmstz.algamoney.api.repositories;

import io.github.tiagoadmstz.algamoney.api.models.Registry;
import io.github.tiagoadmstz.algamoney.api.repositories.queries.RegistryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends JpaRepository<Registry, Long>, RegistryRepositoryQuery {
}
