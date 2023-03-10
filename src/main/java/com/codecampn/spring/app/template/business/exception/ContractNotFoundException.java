package com.codecampn.spring.app.template.business.exception;

public class ContractNotFoundException extends RuntimeException {

    public final ErrorCode errorCode = ErrorCode.CONTRACT_NOT_FOUND;

    public ContractNotFoundException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode.getErrorValue();
    }
}
