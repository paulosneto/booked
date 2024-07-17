package com.agenda.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class ProblemDetailsClass extends RuntimeException{

    public ProblemDetail errorProblemDetail(String message, int statusCode){
        //var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        //pb.setDetail("Server internal error.");
        //pb.setTitle("Server internal error.");
        //pb.setStatus(500);
        pb.setTitle(message);
        pb.setStatus(statusCode);

        return pb;
    }
}
