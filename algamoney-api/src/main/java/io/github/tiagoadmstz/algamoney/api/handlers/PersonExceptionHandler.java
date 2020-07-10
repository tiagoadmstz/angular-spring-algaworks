package io.github.tiagoadmstz.algamoney.api.handlers;

import io.github.tiagoadmstz.algamoney.api.services.exceptions.NonexistentPersonOrInactivePersonException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NonexistentPersonOrInactivePersonException.class)
    public ResponseEntity<Object> handleNonexistentPersonOrInactivePersonException(NonexistentPersonOrInactivePersonException exception) {
        String userMessage = messageSource.getMessage("person.nonexistent-or-inactive", null, LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(exception);
        return ResponseEntity.badRequest().body(new Error(userMessage, developerMessage).toList());
    }

}
