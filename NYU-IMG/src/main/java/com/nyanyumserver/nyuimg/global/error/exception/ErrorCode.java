package com.nyanyumserver.nyuimg.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    BAD_REQUEST(400, "C007", " Bad Request"),
    UNSUPPORTED_MEDIA_TYPE(415, "C008", "UNSUPPORTED_MEDIA_TYPE"),
    FILE_SIZE_EXCEED(416, "C009", "REQUESTED_RANGE_NOT_SATISFIABLE"),
    EXPECTATION_FAILED(417, "C010", "EXPECTATION_FAILED")



    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}