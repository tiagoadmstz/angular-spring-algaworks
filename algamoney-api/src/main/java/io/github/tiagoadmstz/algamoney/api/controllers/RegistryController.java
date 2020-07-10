package io.github.tiagoadmstz.algamoney.api.controllers;

import io.github.tiagoadmstz.algamoney.api.events.CreateEvent;
import io.github.tiagoadmstz.algamoney.api.models.Registry;
import io.github.tiagoadmstz.algamoney.api.repositories.RegistryRepository;
import io.github.tiagoadmstz.algamoney.api.repositories.filters.RegistryFilter;
import io.github.tiagoadmstz.algamoney.api.services.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/registries")
public class RegistryController {

    @Autowired
    private RegistryRepository registryRepository;
    @Autowired
    private RegistryService registryService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Registry> search(RegistryFilter registryFilter, Pageable pageable) {
        return registryRepository.filter(registryFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registry> findById(@PathVariable Long id) {
        return registryRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Registry> create(@Valid @RequestBody Registry registry, HttpServletResponse response) {
        Registry createdRegistry = registryService.save(registry);
        publisher.publishEvent(new CreateEvent(this, response, createdRegistry.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRegistry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        registryRepository.deleteById(id);
    }

}
