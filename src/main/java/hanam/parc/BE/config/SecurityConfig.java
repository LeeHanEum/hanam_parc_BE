package hanam.parc.BE.config;

import hanam.parc.BE.auth.jwt.config.JwtSecurityConfig;
import hanam.parc.BE.auth.jwt.handler.JwtAccessDeniedHandler;
import hanam.parc.BE.auth.jwt.jwt.JwtAuthenticationEntryPoint;
import hanam.parc.BE.auth.jwt.filter.JwtFilter;
import hanam.parc.BE.auth.jwt.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private static final String[] SWAGGER_PATTERNS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-resources",
            "/v2/api-docs"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return new JwtSecurityConfig(jwtTokenProvider).configureAndReturn(
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .cors((cors) ->
                                cors.configurationSource(corsConfigurationSource())
                        )
                        .exceptionHandling((exceptionHandling) ->
                                exceptionHandling
                                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        )
                        .sessionManagement((sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests((authorizeHttpRequests) ->
                                        authorizeHttpRequests
//                                .requestMatchers("/", "/**").permitAll()
//                                .requestMatchers("/auth/login").permitAll()
//                                .requestMatchers("/auth/signup").permitAll()
//                                .requestMatchers(SWAGGER_PATTERNS).permitAll()
                                                .anyRequest().permitAll()
                        )
        ).build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
