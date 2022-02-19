package service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ApiValidationException extends RuntimeException{

    private final String message;
    private final String code;
    private final Throwable error;


    public ApiValidationException(String message, String code, Throwable error) {
        this.message = message;
        this.code = code;
        this.error = error;
    }

    public ApiValidationException(String message) {
        this(message,null,null);
    }
}
