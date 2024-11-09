package com.aula.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice //Anotação de Filtro de Exceções
public class TratadorDeErro {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> tratarErro404(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageException(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().
                map(erro -> new ResponseMessageException(erro.getField() + ", " + erro.getDefaultMessage())));
    }
}
