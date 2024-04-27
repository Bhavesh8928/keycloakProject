package com.key.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity   //this need to be enable becausing we are validating on method level authorization
public class SecurityConfigOrig {

    @Autowired
    JwtAuthConverter jwtAuthConverter;    // we have made own converter in security pacakage

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
//        http.csrf(t -> t.disable());   // they suggested
        http.csrf(AbstractHttpConfigurer::disable);  // self made
        http.authorizeHttpRequests(authorize -> {
//            authorize.anyRequest().authenticated();   // here we are asking for authentication for every request
            authorize
                    .requestMatchers(HttpMethod.GET, "/users/home").permitAll()  // here we are allowing GET request for "/home" without authentication
                    .anyRequest().authenticated();
        });
        http.oauth2ResourceServer(t -> {
//            t.opaqueToken(Customizer.withDefaults()); // Here we are using Opaque Token and
//            t.jwt(Customizer.withDefaults());  // Here we are using JWT and
//            t.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtAuthConverter));  // given by them
            t.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter));
            // here we called default jwtAuthenticationConverter on configuration object and passed our self made jwtAuthConverter for authorization
            // "Customizer.withDefaults()" used because we don't want to override any of the internal functionality
        });
        http.sessionManagement(
                // we do not need spring security session - so we kept here policy as STATELESS
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
    @Bean
    public DefaultMethodSecurityExpressionHandler msecurity() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
                new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");  //it have default role prefix as "ROLE_"
        return defaultMethodSecurityExpressionHandler;
    }
}