package com.shopsphere.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

  private final String[] publicPaths = {
    "/shopsphere-auth-service/api/auth/**",
  };

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http)
    throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeExchange(auth ->
        auth.pathMatchers(publicPaths).permitAll().anyExchange().authenticated()
      )
      .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
      .build();
  }
}
