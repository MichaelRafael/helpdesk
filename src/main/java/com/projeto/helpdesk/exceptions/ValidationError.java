package com.projeto.helpdesk.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(LocalDateTime timestemp, Integer status, String message, String path) {
        super(timestemp, status, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
