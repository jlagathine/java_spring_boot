package com.choisy.website.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.cache.AlwaysValidCacheEntryValidity;

import com.choisy.website.auth.ApplicationUserService;
import com.choisy.website.jwt.JwtUsernamePwdAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityApplication {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService; 
	
	@Autowired
	public SecurityApplication( PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}

	@Bean
    public SecurityFilterChain confFilterChain (HttpSecurity http) throws Exception {
        http
//        	.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        	.csrf(crsf -> crsf.disable())
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	.addFilter(new JwtUsernamePwdAuthFilter(authentication -> authentication))
            .authorizeHttpRequests(authz -> {authz
        	.antMatchers("/").permitAll()
        	.antMatchers("/api/**").hasRole(AppRole.USER.name())
//        	l'ordre des antMatchers est important car le traitement se fait ligne après ligne
        	.antMatchers(HttpMethod.DELETE, "/gestion/api/**").hasAuthority(AppPermission.USER_ECRITURE.getPermission())
        	.antMatchers(HttpMethod.POST, "/gestion/api/**").hasAuthority(AppPermission.USER_ECRITURE.getPermission())
        	.antMatchers(HttpMethod.PUT, "/gestion/api/**").hasAuthority(AppPermission.USER_ECRITURE.getPermission())
        	.antMatchers(HttpMethod.GET, "/gestion/api/**").hasAnyRole(AppRole.ADMIN.name(), AppRole.PDT.name())
        	.anyRequest().authenticated();
            });
//            .httpBasic(Customizer.withDefaults());
//            .formLogin()
//	            .loginPage("/login")
//	            .permitAll()
//	            .defaultSuccessUrl("/user", true)
//	            .passwordParameter("password")
//	            .usernameParameter("username")
//            .and()
//            .rememberMe()
//	            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//	            .key("tropsécure")
//	            .rememberMeParameter("remember-me")
//            .and()
//            .logout()
//            	.logoutUrl("/logout")
//            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//            	.clearAuthentication(true)
//            	.invalidateHttpSession(true)
//        		.deleteCookies("JSESSIONID", "remember-me")
//        		.logoutSuccessUrl("/login");
        return http.build();
	}
	
	@Configuration
	public class SecurityConfiguration {

	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers( "index", "/css/*", "/js/*");
	    }
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider () {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
		
	}
//	protected UserDetailsService userDetailsService() {
//		UserDetails davidUser = User.builder()
//		.username("david")
//		.password(passwordEncoder.encode("mdp"))
////		.roles(AppRole.USER.name())
//		.authorities(AppRole.USER.getGrantedAuthorities())
//		.build();
//		
//		UserDetails mikeUser = User.builder()
//		.username("mike")
//		.password(passwordEncoder.encode("mdp1"))
////		.roles(AppRole.ADMIN.name())
//		.authorities(AppRole.ADMIN.getGrantedAuthorities())
//		.build();
//		
//		UserDetails stacyUser = User.builder()
//		.username("stacy")
//		.password(passwordEncoder.encode("mdp2"))
////		.roles(AppRole.PDT.name())
//		.authorities(AppRole.PDT.getGrantedAuthorities())
//		.build();
//		
//	return new InMemoryUserDetailsManager(
//			davidUser, 
//			mikeUser,
//			stacyUser
//			);	
//	}
}
