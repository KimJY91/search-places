package jy.study.place.exception;

import jy.study.common.exception.ApiException;
import jy.study.common.exception.ApiExceptionCode;

public class SearchPlaceFailException extends ApiException {

    public SearchPlaceFailException() {
        super(ApiExceptionCode.ER01);
    }

    public SearchPlaceFailException(Throwable cause) {
        super(ApiExceptionCode.ER01);
    }
}
