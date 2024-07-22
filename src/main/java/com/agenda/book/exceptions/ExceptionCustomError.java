package com.agenda.book.exceptions;

import org.springframework.http.ProblemDetail;

// Classe que fará o a montagem da mensagem e do status
// de erro para ser devolvida na requisição
public class ExceptionCustomError extends RuntimeException  {

     final String message;
     final int status;

    public ExceptionCustomError(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
