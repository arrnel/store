package org.arrnel.store.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityBeans {

    // PATH
    private static final String PRODUCTS_PATH = "/api/products";                    // add, findAll
    private static final String PRODUCT_PATH = "/api/products/{product_id:\\d+}";   // findById, update, delete

    // AUTHORITIES
    private static final String EDIT_PRODUCTS_SCOPE = "SCOPE_edit_products";
    private static final String VIEW_PRODUCTS_SCOPE = "SCOPE_view_products";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(POST, PRODUCTS_PATH).hasAuthority(EDIT_PRODUCTS_SCOPE)
                        .requestMatchers(PATCH, PRODUCT_PATH).hasAuthority(EDIT_PRODUCTS_SCOPE)
                        .requestMatchers(DELETE, PRODUCT_PATH).hasAuthority(EDIT_PRODUCTS_SCOPE)
                        .requestMatchers(GET).hasAuthority(VIEW_PRODUCTS_SCOPE)
                        .anyRequest().denyAll())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                .build();
    }
}
