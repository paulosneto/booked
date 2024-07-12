package com.agenda.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class ProblemDetailsClass extends RuntimeException{

    public ProblemDetail errorProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setDetail("Server internal error.");
        pb.setStatus(500);

        return pb;
    }
}
