package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
	
		http.authorizeRequests()
		.antMatchers("/emp/find/**").permitAll()
		.antMatchers("/emp/save").hasRole("ADMIN")
		.antMatchers("/emp/").hasRole("USER")
		.and().httpBasic().and().csrf().disable();
		
		
		return http.build();
	}
	
	@Autowired
	public void mycredentials(AuthenticationManagerBuilder auth) throws Throwable{
		auth.inMemoryAuthentication().withUser("shantanu").password("welcome1").roles("USER","ADMIN");
		auth.inMemoryAuthentication().withUser("pavan").password("welcome1").roles("USER");
		auth.inMemoryAuthentication().withUser("jacob").password("welcome1").roles("DEV");
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
