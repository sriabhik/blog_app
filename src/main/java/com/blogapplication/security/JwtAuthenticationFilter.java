package com.blogapplication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenHelper jwtTokenHepler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	        //get Token   
		 	final String requestTokenHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwtToken = null;
	        System.out.println(requestTokenHeader);
	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	            //yes,token w/o bearer
	            jwtToken = requestTokenHeader.substring(7);
	            try {
	                username = jwtTokenHepler.extractUsername(jwtToken);
	            } 
	            catch (ExpiredJwtException e) {
	                e.printStackTrace();
	            } 
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        } 
	        else {
	            System.out.println("Invalid Token,not start wth bearer");
	        }
	        
	        //validate
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            if (this.jwtTokenHepler.validateToken(jwtToken, userDetails)) {
	                //token is valid
	                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
	            } 
	            else {
	                System.out.println("Token is not valid");
	            }

	        }
	        filterChain.doFilter(request, response);
	    }

}
