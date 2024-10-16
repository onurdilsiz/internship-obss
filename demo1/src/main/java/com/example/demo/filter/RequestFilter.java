package com.example.demo.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(2)
@Slf4j
@Component
public class RequestFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("RequestFilter is called");
        log.info(servletRequest.getRemoteAddr());
        servletRequest.setAttribute("demo","demo1");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("RequestFilter is finished");
    }
}
