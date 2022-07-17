package jy.study.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonResponse<T> extends ResponseEntity<CommonResponseBody<T>> {


    public CommonResponse() {
        super(new CommonResponseBody<T>(), HttpStatus.OK);
    }

    public CommonResponse(T body) {
        super(new CommonResponseBody<T>(body), HttpStatus.OK);
    }

    public CommonResponse(String code, String message, T body) {
        super(new CommonResponseBody<>(code, message, body), HttpStatus.OK);
    }

    public CommonResponse(String code, String message, T body, HttpStatus status) {
        super(new CommonResponseBody<>(code, message, body), status);
    }
}
