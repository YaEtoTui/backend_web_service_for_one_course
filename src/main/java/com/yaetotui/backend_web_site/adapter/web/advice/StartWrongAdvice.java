package com.yaetotui.backend_web_site.adapter.web.advice;

import com.yaetotui.backend_web_site.common.exception.StartWrongCabinetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StartWrongAdvice {

    @ExceptionHandler({StartWrongCabinetException.class})
    public ResponseEntity<String> startWrong(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
