package hanam.parc.BE.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static javax.management.Query.in;
import static org.springframework.data.util.TypeUtils.type;

//@Configuration
//@EnableWebMvc
//@OpenAPIDefinition(
//        info = @io.swagger.v3.oas.annotations.info.Info(
//                title = "하남시 장애인 체육회 API",
//                version = "v1.0.0",
//                description = "하남시 장애인 체육회 API 문서입니다."
//        )
//)
//public class SwaggerConfig {
//
//    private static final String REFERENCE = "Authorization";
//
//    @Bean
//    public OpenAPI openAPI() {
//        SecurityScheme securityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                .in(SecurityScheme.In.HEADER).name(REFERENCE);
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
//
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
//                .security(Arrays.asList(securityRequirement));
//    }
//}

@Configuration
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
