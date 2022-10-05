package com.choisy.website.jwt;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernamePwdAuthFilter extends UsernamePasswordAuthenticationFilter  {

	private final AuthenticationManager authenticationManager;

	public JwtUsernamePwdAuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
											HttpServletResponse response)  throws AuthenticationException {
		
	try {
		UsernamePswdAuthReq req = 	new ObjectMapper().readValue(request.getInputStream(), UsernamePswdAuthReq.class);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
		
		Authentication autentificate = authenticationManager.authenticate(authentication);
		return autentificate;
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
	@Override
	protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{
		String key = "superSecuresuperSecuresuperSecuresuperSecuresuperSecure";
						String token = Jwts.builder().setSubject(authResult.getName())
											.claim("authorities", authResult.getAuthorities())
											.setIssuedAt(new Date())
											.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
											.signWith(Keys.hmacShaKeyFor(key.getBytes()))
											.compact();
		
		response.addHeader("Authorization", "Bearer"+token);
	}
	
}
