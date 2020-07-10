package io.github.tiagoadmstz.algamoney.api.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Error {

    private String userMessage;
    private String developerMessage;

    public List<Error> toList() {
        return Collections.singletonList(this);
    }

    public List<Error> createErrorList(BindingResult bindingResult, MessageSource messageSource) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new Error(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()), fieldError.toString()))
                .collect(Collectors.toList());
    }

}
