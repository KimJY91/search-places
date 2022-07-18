package jy.study.common.exception;

public enum ApiExceptionCode {

    ER00("서버에 문제가 발생했습니다."),
    ER01("장소 조회에 실패했습니다. 잠시 후 다시 시도해주세요."),

    ;

    private final String message;

    ApiExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return this.name();
    }
}
