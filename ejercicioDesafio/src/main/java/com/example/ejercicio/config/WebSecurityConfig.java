package com.example.ejercicio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ejercicio.config.jwt.JwtAuthRequestFilter;
import com.example.ejercicio.config.jwt.JwtAuthenticationEntryPoint;
import com.example.ejercicio.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final UserDetailsServiceImpl userDetailsService;

	private final JwtAuthRequestFilter jwtRequestFilter;

	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			UserDetailsServiceImpl userDetailsService, JwtAuthRequestFilter jwtRequestFilter) {
		super();
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/v3/**", "/swagger-ui/**","/console/**", "/api-docs/**","/api/user/register","api/user/register")
						.permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.userDetailsService(userDetailsService).build();

	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
