package jy.study.common.exception;

public class ApiException extends RuntimeException {
    private final String code;
    private final String message;

    public ApiException(ApiExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public ApiException(ApiExceptionCode exceptionCode, Throwable cause) {
        super(cause);
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
