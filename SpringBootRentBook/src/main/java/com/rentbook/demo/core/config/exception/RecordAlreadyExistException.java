package com.rentbook.demo.core.config.exception;

public class RecordAlreadyExistException extends RuntimeException {


    private final Long id;

    public RecordAlreadyExistException(Long id) {
        super(id + " is exist");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}