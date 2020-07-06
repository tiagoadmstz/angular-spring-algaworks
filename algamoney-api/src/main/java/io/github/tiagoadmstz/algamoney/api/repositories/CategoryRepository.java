package io.github.tiagoadmstz.algamoney.api.repositories;

import io.github.tiagoadmstz.algamoney.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
