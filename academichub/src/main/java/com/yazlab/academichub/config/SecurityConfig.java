package com.yazlab.academichub.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity.sessionManagement(management -> management.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated()
                        // .requestMatchers("/api/candidate/**").permitAll()
                        //ekleme yapilacak...
                        .anyRequest().permitAll()

        )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {


        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Collections.singletonList("*"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setExposedHeaders(Collections.singletonList("Authorization"));
                cfg.setMaxAge(3600L);
                //Tarayıcı bu CORS yapılandırmasını 1 saat boyunca önbelleğe alır.
                return cfg;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
