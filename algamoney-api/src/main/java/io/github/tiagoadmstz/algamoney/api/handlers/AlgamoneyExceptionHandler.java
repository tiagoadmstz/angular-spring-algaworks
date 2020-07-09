package io.github.tiagoadmstz.algamoney.api.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
        String developerMessage = Optional.ofNullable(exception.getCause()).orElse(exception).toString();
        return handleExceptionInternal(exception,
                Arrays.asList(new Error(userMessage, developerMessage)),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, createErrorList(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
        String developerMessage = exception.toString();
        return handleExceptionInternal(exception,
                Arrays.asList(new Error(userMessage, developerMessage)),
                HttpHeaders.EMPTY,
                HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.operation-not-permitted", null, LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(exception);
        return handleExceptionInternal(exception,
                Arrays.asList(new Error(userMessage, developerMessage)),
                HttpHeaders.EMPTY,
                HttpStatus.BAD_REQUEST, request
        );
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new Error(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()), fieldError.toString()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    protected class Error {

        private String userMessage;
        private String developerMessage;

    }

}
