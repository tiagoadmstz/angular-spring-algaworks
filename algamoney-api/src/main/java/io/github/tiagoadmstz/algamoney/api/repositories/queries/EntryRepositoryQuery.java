package io.github.tiagoadmstz.algamoney.api.repositories.queries;

import io.github.tiagoadmstz.algamoney.api.models.Entry;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.EntryFilter;
import io.github.tiagoadmstz.algamoney.api.repositories.projections.SummaryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntryRepositoryQuery {

    Page<Entry> filter(EntryFilter entryFilter, Pageable pageable);

    Page<SummaryEntry> summarize(EntryFilter entryFilter, Pageable pageable);

}
