package com.mb.cards.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ApiLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        log.info("");
        log.info("REQUEST [{} {}] RECEIVED FROM IP: {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        filterChain.doFilter(request, response);
        log.info("Response [{}] for [{}]", response.getStatus(), request.getRequestURI());
//        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request, 10240);
//        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);
//
//        filterChain.doFilter(req, res);
//
//        logRequest(req);
//
//        res.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper req) {
        String url = req.getRequestURI();
        String method = req.getMethod();
        String body = new String(req.getContentAsByteArray(), StandardCharsets.UTF_8);

        System.out.println("============ REQUEST ============");
        System.out.println(method + " " + url);
        System.out.println("BODY:");
        System.out.println(body);
        System.out.println("=================================");
    }
}

