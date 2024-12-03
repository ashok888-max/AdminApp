package com.app.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.demo.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/saveAdmin",
						"/api/**","/getDocument/**").permitAll() // Permit
				.requestMatchers("/home", "/users", "/hospital", "/patient", "/addHospital", "/addDoctor")
				.authenticated() // Protect pages
				.anyRequest().authenticated().and().formLogin().loginPage("/login") // Custom login page
				.loginProcessingUrl("/perform_login") // URL to submit the username and password
				.usernameParameter("identifier").passwordParameter("password").defaultSuccessUrl("/home", true)
				.failureUrl("/login?error=true").permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.permitAll();

		return http.build();
	}

}
