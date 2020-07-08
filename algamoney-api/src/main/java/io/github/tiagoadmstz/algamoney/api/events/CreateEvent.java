package io.github.tiagoadmstz.algamoney.api.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class CreateEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4398450121201629667L;
    private HttpServletResponse response;
    private Long id;

    public CreateEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

}
