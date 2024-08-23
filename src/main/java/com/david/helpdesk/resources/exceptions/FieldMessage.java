package com.david.helpdesk.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class FieldMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = -4469530611866656366L;

    private String fieldName;
    private String message;

    public FieldMessage() {
        super();
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
