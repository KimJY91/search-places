package jy.study.common.config.advice;

import jy.study.common.dto.CommonResponseBody;
import jy.study.common.exception.ApiException;
import jy.study.common.exception.ApiExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CommonResponseBody> apiExceptionHandler(ApiException exception) {
        log.error("ApiException ", exception);

        if (exception.getCause() != null) {
            log.error("ApiException.cause ", exception.getCause());
        }

        CommonResponseBody commonResponseBody = new CommonResponseBody<>(exception.getCode(), exception.getMessage());
        return new ResponseEntity(commonResponseBody, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseBody> exceptionHandler(Exception exception) {
        log.error("exception ", exception);

        ApiExceptionCode exceptionCode = ApiExceptionCode.ER00;
        CommonResponseBody commonResponseBody = new CommonResponseBody<>(exceptionCode.getCode(), exceptionCode.getMessage());
        return new ResponseEntity(commonResponseBody, HttpStatus.OK);
    }

}
