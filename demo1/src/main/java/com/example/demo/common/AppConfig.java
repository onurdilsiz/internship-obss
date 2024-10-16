package com.example.demo.common;

import com.example.demo.interceptor.RequestInterceptor;
import com.example.demo.interceptor.RequestInterceptor2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;
    private final RequestInterceptor2 requestInterceptor2;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
        registry.addInterceptor(requestInterceptor2);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
