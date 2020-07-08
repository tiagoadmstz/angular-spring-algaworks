package io.github.tiagoadmstz.algamoney.api.events.listeners;

import io.github.tiagoadmstz.algamoney.api.events.CreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreateListener implements ApplicationListener<CreateEvent> {

    @Override
    public void onApplicationEvent(CreateEvent createEvent) {
        addLocationHeader(createEvent.getId(), createEvent.getResponse());
    }

    private void addLocationHeader(Long id, HttpServletResponse response) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}
