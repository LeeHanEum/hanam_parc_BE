package hanam.parc.BE.auth.jwt.interceptor;

import hanam.parc.BE.auth.jwt.util.TokenUtils;
import hanam.parc.BE.exception.ApplicationException;
import hanam.parc.BE.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception {

        // favicon.ico 요청에 대한 JWT 토큰 검증을 건너뛰기
        if (request.getRequestURI().equals("/favicon.ico")) {
            return true;
        }

        String token = null;

        // 쿠키에서 JWT 토큰 가져오기
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            if (TokenUtils.isValidToken(token)) {
                String userId = TokenUtils.getUserIdFromToken(token);
                if (userId == null) {
                    log.debug("token isn't userId");
                    throw new ApplicationException(ErrorCode.AUTH_TOKEN_NOT_MATCH);
                }
                return true;
            } else {
                throw new ApplicationException(ErrorCode.AUTH_TOKEN_INVALID);
            }
        } else {
            throw new ApplicationException(ErrorCode.AUTH_TOKEN_IS_NULL);
        }
    }


}