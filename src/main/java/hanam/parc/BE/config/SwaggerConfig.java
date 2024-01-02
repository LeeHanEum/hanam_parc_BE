package hanam.parc.BE.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info  = new Info()
                .title("하남시 장애인 체육회 api")
                .description("하남시 장애인 체육회 api 문서입니다.")
                .version("v1.0.0");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
