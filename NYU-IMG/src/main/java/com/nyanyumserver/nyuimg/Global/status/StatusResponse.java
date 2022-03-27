package com.nyanyumserver.nyuimg.Global.status;

import com.nyanyumserver.nyuimg.Global.status.StatusCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatusResponse {

    private String message;
    private int status;
    private String entity;
    private URI redirect;


    public StatusResponse(final StatusCode code, final String res) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.entity = res;
    }

    public StatusResponse(final StatusCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
    }

    public StatusResponse(final StatusCode code, final URI redirect) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.redirect = redirect;
    }

}
