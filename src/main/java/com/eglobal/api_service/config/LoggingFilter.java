package com.eglobal.api_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;

@Log4j2
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Log de Request
        log.info("=== REQUEST ===");
        log.info("Method: {}", request.getMethod());
        log.info("URI: {}", request.getRequestURI());
        log.info("QueryString: {}", request.getQueryString());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            log.info("Header: {} = {}", header, request.getHeader(header));
        }

        request.getParameterMap().forEach((key, values) ->
                log.info("Param: {} = {}", key, Arrays.toString(values))
        );

        filterChain.doFilter(request, response);

        log.info("=== RESPONSE ===");
        log.info("Status: {}", response.getStatus());

        Collection<String> headerNamesResp = response.getHeaderNames();
        for (String header : headerNamesResp) {
            log.info("Header: {} = {}", header, response.getHeader(header));
        }
    }
}
