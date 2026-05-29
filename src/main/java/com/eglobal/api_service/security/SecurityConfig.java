package com.eglobal.api_service.security;

import com.eglobal.api_service.exception.InvalidApiKeyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore((servletRequest, servletResponse, chain) -> {
                    HttpServletRequest request = (HttpServletRequest) servletRequest;
                    HttpServletResponse response = (HttpServletResponse) servletResponse;

                    String headerKey = request.getHeader("X-API-KEY");
                    log.info("Service recibió API Key: {}", headerKey);

                    if (headerKey == null || !headerKey.equals(apiKey)) {
                        throw new InvalidApiKeyException("API Key inválida o ausente");
                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("apiKeyUser", null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

