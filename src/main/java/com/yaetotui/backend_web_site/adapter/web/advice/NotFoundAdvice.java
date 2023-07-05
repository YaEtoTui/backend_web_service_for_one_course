package com.yaetotui.backend_web_site.adapter.web.advice;

import com.yaetotui.backend_web_site.common.exception.NotFoundCabinetException;
import com.yaetotui.backend_web_site.common.exception.NotFoundCampusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler({NotFoundCampusException.class, NotFoundCabinetException.class})
    public ResponseEntity<String> notFound(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
