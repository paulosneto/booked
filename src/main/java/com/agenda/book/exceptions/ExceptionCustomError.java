package com.agenda.book.exceptions;

import org.springframework.http.ProblemDetail;

public class ExceptionCustomError extends ProblemDetailsClass  {

    private final String detail;
    private int status;

    public ExceptionCustomError(String detail, int status) {
        this.detail = detail;
        this.status = status;
    }

    @Override
    public ProblemDetail errorProblemDetail() {
        var pd = ProblemDetail.forStatus(status);
        pd.setDetail(detail);
        return pd;
    }
}
