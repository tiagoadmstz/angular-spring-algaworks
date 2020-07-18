package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.events.CreateEvent;
import io.github.tiagoadmstz.algamoney.api.models.Entry;
import io.github.tiagoadmstz.algamoney.api.repositories.EntryRepository;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.EntryFilter;
import io.github.tiagoadmstz.algamoney.api.services.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/registries")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EntryController {

    private final EntryRepository entryRepository;
    private final EntryService entryService;
    private final ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_ENTRY') and #oauth2.hasScope('read')")
    public Page<Entry> search(EntryFilter entryFilter, Pageable pageable) {
        return entryRepository.filter(entryFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_ENTRY') and #oauth2.hasScope('read')")
    public ResponseEntity<Entry> findById(@PathVariable Long id) {
        return entryRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_ENTRY') and #oauth2.hasScope('write')")
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry createdEntry = entryService.save(entry);
        publisher.publishEvent(new CreateEvent(this, response, createdEntry.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_ENTRY') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        entryRepository.deleteById(id);
    }

}
