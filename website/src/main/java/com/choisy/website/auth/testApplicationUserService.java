package com.choisy.website.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.choisy.website.security.AppRole;
import com.google.common.collect.Lists;

@Repository("fake")
public class testApplicationUserService implements ApplicationUserDao {
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public testApplicationUserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {			
		return  getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
						"david",
						passwordEncoder.encode("mdp"), 
						AppRole.USER.getGrantedAuthorities(), 
						true, 
						true, 
						true, 
						true),
				new ApplicationUser(
						passwordEncoder.encode("mdp"), 
						"mike", 
						AppRole.ADMIN.getGrantedAuthorities(), 
						true, 
						true, 
						true, 
						true),
				new ApplicationUser(
						passwordEncoder.encode("mdp"), 
						"stacy", 
						AppRole.PDT.getGrantedAuthorities(), 
						true, 
						true, 
						true, 
						true)
				);
		return applicationUsers;
	}

//	public PasswordEncoder getPasswordEncoder() {
//		return passwordEncoder;
	
}
