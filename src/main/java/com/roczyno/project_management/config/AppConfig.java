package com.roczyno.project_management.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize->Authorize
                        .requestMatchers("v3/api-docs/**","/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.configurationSource(CorsConfigurationSource()));
        return http.build();
    }


    private CorsConfigurationSource CorsConfigurationSource() {
        return request -> {
			CorsConfiguration cfg = new CorsConfiguration();
			cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:5000"));
			cfg.setAllowedMethods(Collections.singletonList("*"));
			cfg.setAllowedHeaders(Collections.singletonList("*"));
			cfg.setAllowCredentials(true);
			cfg.setExposedHeaders(List.of("Authorization"));
			cfg.setMaxAge(3600L);
			return cfg;
		};
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}
