package com.david.helpdesk.resources.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = -2174958227049535269L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


//    Construtores
    public StandardError() {
        super();
    }

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
