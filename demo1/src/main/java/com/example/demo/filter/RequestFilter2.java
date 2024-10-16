package com.example.demo.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(3)
@Slf4j
@Component
public class RequestFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("RequestFilter2 is called");
        log.info(servletRequest.getAttribute("demo").toString());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("RequestFilter2 is finished");
    }
}
