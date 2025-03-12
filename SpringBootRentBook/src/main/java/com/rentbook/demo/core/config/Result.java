package com.rentbook.demo.core.config;

import lombok.Getter;

@Getter
public class Result {

    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
