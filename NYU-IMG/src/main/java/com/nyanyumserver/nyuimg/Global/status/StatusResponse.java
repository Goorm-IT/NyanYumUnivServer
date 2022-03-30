package com.nyanyumserver.nyuimg.Global.status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;

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
