package com.akampany.api.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.akampany.api.User.AppUserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,AppUserRepository userRepo, AuthenticationManager authenticationManager,
			JwtService jwtService) throws Exception {
    http
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
      	.authenticationProvider(authenticationProvider)
        .authorizeHttpRequests()
        .requestMatchers(
        		"/auth/**"
                
        )
        .permitAll()

          .anyRequest()
          .authenticated()
        .and()
          
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;
    http.addFilter(new JwtAuthenticationFilter(userRepo,authenticationManager,jwtService));
    
    http.addFilterBefore( new JwtAuthorizationFilter(jwtService,userRepo),JwtAuthenticationFilter.class);
    return http.build();
  }
}
