package com.example.demo.config;

import com.example.demo.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserService userService;

	@Value("${jwt.token.secret}")
	private String secretKey;
	@Value("${jwt.token.expire}")
	private static long expireTimeMs = 1000 * 60 * 60;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(
				authorize -> authorize
					.requestMatchers("api/v1/ably/auth/join").permitAll()
					.requestMatchers("api/v1/ably/auth/login").permitAll()
					.anyRequest().authenticated()
			);
		return http.build();
	}
}