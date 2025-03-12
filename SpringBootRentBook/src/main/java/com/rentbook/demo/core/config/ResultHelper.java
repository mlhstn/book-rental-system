package com.rentbook.demo.core.config;


import com.rentbook.demo.dto.response.BookResponse;

public class ResultHelper {
    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, "201", Msg.CREATED, data);
    }

    public static <T> ResultData<T> RecordAlreadyExistsError(Long id, T data) {
        return new ResultData<>(false, "409", Msg.RECORD_ALREADY_EXISTS + id, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, "200", Msg.OK, data);
    }
}
