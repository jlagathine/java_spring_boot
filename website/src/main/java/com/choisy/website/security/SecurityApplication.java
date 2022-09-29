package com.choisy.website.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityApplication {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//            .authorizeHttpRequests//((authz) -> authz
//            	.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                .anyRequest()
//                .authenticated()
//            )
            .httpBasic();
        return http.build();
	}
	
//  return http
//			.antMatcher("")
//			.authorizeRequests(authorize -> authorize
//					.anyRequest().authenticated()
//			)
//			.build();
	
	@Configuration
	public class SecurityConfiguration {

	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/", "index", "/css/*", "/js/*");
	    }
	}
}
