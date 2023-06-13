package com.inbank.decision.exceptions;

public class GeneralException extends RuntimeException {
    public GeneralException(String errorMessage) {
        super(errorMessage);
    }
}
