package com.example.demo.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Order(1)
@Slf4j
@Component
public class RequestFilter3 extends CommonsRequestLoggingFilter {
    @Override
    protected boolean shouldLog(HttpServletRequest request) {

        log.info(request.getRequestURI());
        if(request.getRequestURI().contains("actuator")){
            return true;
        }
        return super.shouldLog(request);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        log.info(request.getRequestURI());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        log.info(request.getRequestURI());
    }
}
