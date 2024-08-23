package com.david.helpdesk.resources.exceptions;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    @Serial
    private static final long serialVersionUID = 8452603247210122786L;

    private List<FieldMessage> errors = new ArrayList<>();


    // Construtor
    public ValidationError(long l, int value, String erroNaValidaçãoDosCampos, String message, String requestURI) {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path, List<FieldMessage> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldname, String message) {
        this.errors.add(new FieldMessage(fieldname, message));
    }
}
