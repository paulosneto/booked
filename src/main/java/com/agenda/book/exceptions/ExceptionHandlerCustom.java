package com.agenda.book.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Classe criada para lançar a requisição um codigo de erro tratado
@RestControllerAdvice
public class ExceptionHandlerCustom extends Throwable {

    // Exceção usada para retornar mensagem caso o
    // objeto a ser procurado no banco ja exista
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry(String message){
        ExceptionDTO dto = new ExceptionDTO(message, "400");
        return ResponseEntity.badRequest().body(dto);
    }


    // Exceção disparada quando o objeto procurado nao for encontrado no banco
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notfound(String message){
        ExceptionDTO dto = new ExceptionDTO(message , "404");
        return  ResponseEntity.notFound().build();
    }

    // Exceção usada para disparar erro interno de servidor para requisição
    @ExceptionHandler(Exception.class)
    public ResponseEntity genericException(String message){
        ExceptionDTO dto = new ExceptionDTO(message, "500");
        return  ResponseEntity.internalServerError().body(dto);
    }

}
