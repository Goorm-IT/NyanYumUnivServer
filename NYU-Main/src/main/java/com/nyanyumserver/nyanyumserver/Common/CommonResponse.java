package com.nyanyumserver.nyanyumserver.Common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse {
        private String message;
        private int status;
        private List<FieldError> errors;
        private String code;

        private CommonResponse(final CommonConst code) {
            this.message = code.getMessage();
            this.status = code.getStatus();
            this.code = code.getCode();
            this.errors = new ArrayList<>();
        }

public static CommonResponse of(final CommonConst code) {
    return new CommonResponse(code);
}


}
