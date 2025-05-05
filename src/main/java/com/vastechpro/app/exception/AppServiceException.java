package com.vastechpro.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppServiceException extends Exception {
    private final String errorMessage;

    public AppServiceException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
