package com.agenda.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProblemDetailsClass extends  RuntimeException{

    String message;
    int status;

    public ProblemDetailsClass(String message, int status) {
        this.message = message;
        this.status = status;

        }
}
