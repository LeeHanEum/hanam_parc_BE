package hanam.parc.BE.type.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.http.HttpHeaders;

@Getter
@AllArgsConstructor
public class ResponseModel<T> {

    private boolean success;

    private T data;

    private Error error;

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<>(true, data, null);
    }

    public static <T> ResponseModel<T> successHeader(T data, HttpHeaders headers) {
        return new ResponseModel<>(true, data, null);
    }

    public static <T> ResponseModel<T> fail(Error error) {
        return new ResponseModel<>(false, null, error);
    }

    @Getter
    @AllArgsConstructor
    public static class Error {

        private String code;

        private String message;

    }
}
