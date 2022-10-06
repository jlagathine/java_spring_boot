package com.choisy.website.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifier extends OncePerRequestFilter {
    
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

	public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(jwtConfig.getAutorization());
		
		if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefx())){
			filterChain.doFilter(request, response);
			return;
			}
		String token = authorizationHeader.replace(jwtConfig.getTokenPrefx(), "");
	try {
		
//		String secretKey = "superSecuresuperSecuresuperSecuresuperSecuresuperSecure";
//		Jws<Claims> claimsJws = Jwts.parser()
//			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
//			.parseClaimsJws(token);
		
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token);
		
		Claims body = claimsJws.getBody();
		String username = body.getSubject();
		
		@SuppressWarnings("unchecked")
        var authorities = (List<Map<String, String>>) body.get("authoroties");
		
		Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch(JwtException e)
		{throw new IllegalStateException(String.format("Le token %s n'est pas fiable", token));
	}
	
	filterChain.doFilter(request, response);
}

}
