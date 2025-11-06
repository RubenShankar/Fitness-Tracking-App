package com.MVC.demo.SecurityConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
	
	
	@Bean
	@ConditionalOnMissingBean
	public SecurityFilterChain security(HttpSecurity http) throws Exception
	{                                                                              // hasanyrole for multiple user to access same apis
		http.authorizeHttpRequests(auth-> auth.requestMatchers("/workout/list").hasAnyRole("user","customer").requestMatchers("/deposite","/statement")
				.permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public UserDetailsService userdetailsservice(PasswordEncoder encoder)
	{
		
		UserDetails u1= User.withUsername("Ruben").password(encoder.encode("Ruben@123")).roles("user").build();
		UserDetails u2= User.withUsername("Vamsi").password(encoder.encode("Vamsi@123")).roles("customer").build();
		UserDetails u3= User.withUsername("Suraj").password(encoder.encode("Suraj@123")).roles("admin").build();
		
		return new InMemoryUserDetailsManager(u1,u2,u3);
		

	}
	
	@Bean
	public PasswordEncoder passwordencoder()
	{
		return new BCryptPasswordEncoder();
	}

}
