package com.nyanyumserver.nyanyumserver.Common;

public enum CommonConst {
    OK(200, "S001", "요청을 정상적으로 처리하였습니다."),
    NO_CONTENT(204, "S002", "데이터가 없습니다, 데이터를 추가해주세요."),
    BAD_REQUEST(400, "C001", "요청 구문이 잘못되었습니다."),
    NOT_FOUND(404, "C002", "해당 리소스가 존재하지 않습니다."),
    PAYLOAD_TOO_LARGE(405, "C003", "요청 리소스의 형식이 올바르지 않습니다."),
    UNPROCESSABLE_ENTITY(422, "C004", "처리할 수 없는 (중복)데이터 입니다."),
    INTERNAL_SERVER_ERROR(500, "C005", "요청 중 서버 에러가 발생했습니다.");

    private final String code;
    private final String message;
    private int status;

    CommonConst(final int status, final String code, final String message) {
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
