package com.filmyai.backend.config;

import com.filmyai.backend.config.jwt.JwtAuthenticationFilter;
import com.filmyai.backend.service.SecurityService.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilterChainConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
       return  http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(request -> request
                       .requestMatchers(
                               "/swagger-ui.html",
                               "/swagger-ui/**",
                               "/v3/api-docs/**",
                               "/v3/api-docs",
                               "/swagger-resources/**",
                               "/webjars/**"
                       ).permitAll()
                       .requestMatchers("filmyai/login","filmyai/signup")
                       .permitAll()
                       .anyRequest().authenticated())
               .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    // Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}
