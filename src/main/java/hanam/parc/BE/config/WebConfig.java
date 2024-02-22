package hanam.parc.BE.config;

import hanam.parc.BE.auth.jwt.interceptor.JwtTokenInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Value("${resource.file.path}")
    private String filePath;

    @Value("${resource.file.url}")
    private String fileURL;

    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**") // 모든 URL에 대해 JWT 토큰 검사를 적용합니다.
                .excludePathPatterns(
                        "/login",
                        "/join",
                        "/logout",
                        "/board/**",
                        "/board",
                        "/board/"
                ); // 로그인 페이지는 JWT 토큰 검사에서 제외합니다.
    }

    /**
     * 뷰 컨트롤러를 추가할때 사용된다. 여기서는 루트 URL("/")에 접근했을 때 "/login"으로 리다이렉트하도록 설정하고 있다.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("[+] WebConfig Start !!! ");
        registry.addRedirectViewController("/", "/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Resource File Mapped : {} -> {}", fileURL, filePath);
        registry
                .addResourceHandler("/**")
                .addResourceLocations(fileURL);
    }

}