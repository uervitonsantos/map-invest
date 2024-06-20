package com.map.invest.util.validacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidacaoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    private ResponseEntity<String> usuarioExceptionHandler(ValidacaoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
