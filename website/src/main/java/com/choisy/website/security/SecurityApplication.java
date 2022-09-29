package com.choisy.website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityApplication {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityApplication(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
//            	.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .anyRequest()
                .authenticated()
            )
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
	
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails davidUser = User.builder()
		.username("david")
		.password(passwordEncoder.encode("mdp"))
		.roles("Utilisateurs")
		.build();
	return new InMemoryUserDetailsManager(
			davidUser
			);	
	}
}
