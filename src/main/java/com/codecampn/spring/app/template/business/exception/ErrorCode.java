package com.codecampn.spring.app.template.business.exception;

public enum ErrorCode {

    CONTRACT_NOT_FOUND(100); // just an example value

    private final int errorValue;

    ErrorCode(int errorValue) {
        this.errorValue = errorValue;
    }

    public int getErrorValue() {
        return errorValue;
    }
}
