package com.codecampn.spring.app.template.business.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ContractNotFoundException.class)
    public ProblemDetail handleContractNotFoundException(ContractNotFoundException exception) {
        log.error("ContractNotFoundException, {}", exception.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Contract not found");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setProperty("error_code", exception.getErrorCode());
        //problemDetail.setType(URI.create("test-uri"));
        return problemDetail;
    }
}
