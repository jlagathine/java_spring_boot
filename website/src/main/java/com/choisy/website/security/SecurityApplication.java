package com.choisy.website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityApplication {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityApplication( PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
    public SecurityFilterChain confFilterChain (HttpSecurity http) throws Exception {
        http
        	.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> {authz
        	.antMatchers("/").permitAll()
        	.antMatchers("/api/**").hasRole(AppRole.USER.name())
        	.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults());
        return http.build();
	}
	
	@Configuration
	public class SecurityConfiguration {

	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers( "index", "/css/*", "/js/*");
	    }
	}
	
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails davidUser = User.builder()
		.username("david")
		.password(passwordEncoder.encode("mdp"))
		.roles(AppRole.USER.name())
		.build();
		
		UserDetails mikeUser = User.builder()
		.username("mike")
		.password(passwordEncoder.encode("mdp1"))
		.roles(AppRole.ADMIN.name())
		.build();
		
		UserDetails stacyUser = User.builder()
		.username("stacy")
		.password(passwordEncoder.encode("mdp2"))
		.roles(AppRole.PDT.name())
		.build();
		
	return new InMemoryUserDetailsManager(
			davidUser, 
			mikeUser,
			stacyUser
			);	
	}
}
