package com.example.demo.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


import static com.example.demo.common.Constants.Roles.ROLE_ADMIN;
import static com.example.demo.common.Constants.Roles.ROLE_USER;
import static jakarta.servlet.http.HttpServletResponse.*;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST = { //
            "/api/v1/h2-console", //
            "/api/v1/h2-console/**", //
            "/v3/api-docs/**", //
            "/swagger-ui/**", //
            "/swagger-ui.html", //
            "/swagger-ui/index.html", //
            "/api/v1/webjars/**", //
            "/api/v1/graphiql", //
            "/api/v1/api/graphql", //
    };
    public DaoAuthenticationProvider authenticationProvider (PasswordEncoder passwordEncoder, UserAuthService userAuthService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userAuthService);

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizer -> customizer.requestMatchers(AUTH_WHITELIST).anonymous().anyRequest().hasAnyAuthority(ROLE_ADMIN, ROLE_USER))
                .exceptionHandling(customizer -> customizer
                        .accessDeniedHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN))
                        .authenticationEntryPoint((req, resp, ex) -> resp.setStatus(SC_UNAUTHORIZED)))
                .formLogin(customizer -> customizer
                        .loginProcessingUrl("/login")
                        .successHandler((req, resp, auth) -> resp.setStatus(SC_OK))
                        .failureHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN)))
                .sessionManagement(customizer -> customizer.invalidSessionStrategy((req, resp) -> resp.setStatus(SC_UNAUTHORIZED)))
                .logout(customizer -> customizer.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .csrf(customizer -> customizer.disable());
        return http.build();
    }


//    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(customizer -> customizer.requestMatchers(AUTH_WHITELIST).anonymous().anyRequest().hasAnyAuthority(ROLE_ADMIN, ROLE_USER))
//                .exceptionHandling(customizer -> customizer
//                        .accessDeniedHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN))
//                        .authenticationEntryPoint((req, resp, ex) -> resp.setStatus(SC_UNAUTHORIZED)))
//                .httpBasic(Customizer.withDefaults())
//                .csrf(customizer -> customizer.disable());
//        return http.build();
//    }
}
