package jy.study.common.dto;

import lombok.Getter;

@Getter
public class CommonResponseBody<T> {
    public static final String RESPONSE_CODE_OK = "0";
    private String code;
    private String message;
    private T data;

    public CommonResponseBody() {
        this.code = RESPONSE_CODE_OK;
        this.message = "";
        this.data = null;
    }

    public CommonResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public CommonResponseBody(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponseBody(T data) {
        this.code = RESPONSE_CODE_OK;
        this.message = "";
        this.data = data;
    }
}
