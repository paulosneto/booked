package com.agenda.book.exceptions;

import org.springframework.http.ProblemDetail;

public class ExceptionCustomError extends ProblemDetailsClass  {

    private final String detail;
    private final int status;

    public ExceptionCustomError(String detail, int status) {
        super(detail,status);
        this.detail = detail;
        this.status = status;
    }

    /*@Override
    public ProblemDetail errorProblemDetail(String detail, int status) {
        var pd = ProblemDetail.forStatus(this.status);
        pd.setTitle("ERROR");
        pd.setDetail(this.detail);

        return pd;
    }*/
}
