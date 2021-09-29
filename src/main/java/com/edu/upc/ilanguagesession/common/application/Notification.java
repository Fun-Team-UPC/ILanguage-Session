package com.edu.upc.ilanguagesession.common.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
    private List<java.lang.Error> errors = new ArrayList<>();
    private final String SEPARATOR = "||";

    public void addError(String message) {
        addError(message, null);
    }

    public void addError(String message, Exception cause) {
        errors.add(new java.lang.Error(message, cause));
    }

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public String errorMessage() {
        return errors.stream().map(e -> e.getMessage()).collect(Collectors.joining(SEPARATOR + " "));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
