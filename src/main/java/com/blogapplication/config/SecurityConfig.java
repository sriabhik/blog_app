package com.blogapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogapplication.security.CustomUserDetailService;
import com.blogapplication.security.JwtAuthenticationEntryPoint;
import com.blogapplication.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
			.disable()
			
			.authorizeHttpRequests()
			.antMatchers("/api/auth/login","/api/auth/registerUser","/api/auth/current-user","/actuator/mappings","/actuator").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers(HttpMethod.PUT).permitAll()
			.antMatchers(HttpMethod.GET).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
	}
// this is for getting authenticaton from db along with httpBasaic
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}

//BcryptPasswordEncoder
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
