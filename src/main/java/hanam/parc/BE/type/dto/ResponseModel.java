package hanam.parc.BE.type.dto;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.io.PrintWriter;


@Getter
@Builder
@AllArgsConstructor
public class ResponseModel<T> {

    private boolean success;

    private T data;

    private Error error;

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<>(true, data, null);
    }

    public static <T> ResponseModel<T> successHeader(T data, HttpHeaders httpHeaders) {
        return new ResponseModel<>(true, data, null);
    }

    public static <T> ResponseModel<T> fail(String code, String message) {
        return new ResponseModel<>(false, null, new Error(code, message));
    }

    @Getter
    @AllArgsConstructor
    public static class Error {

        private String code;

        private String message;

    }
}
