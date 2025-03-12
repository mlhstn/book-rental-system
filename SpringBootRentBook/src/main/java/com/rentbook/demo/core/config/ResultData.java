package com.rentbook.demo.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData<T> extends Result {

    private T data;

    public ResultData(boolean status, String code, String message,T data) {
        super(status, code, message);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}