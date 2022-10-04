package com.choisy.website.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;


public enum AppRole {
	USER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(AppPermission.USER_ECRITURE, AppPermission.USER_LECTURE)),
	PDT(Sets.newHashSet(AppPermission.USER_LECTURE));

	private final Set<AppPermission> permission;

	private AppRole(Set<AppPermission> permission) {
		this.permission = permission;
	}

	public Set<AppPermission> getPermission() {
		return permission;
	}
	 
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permission = getPermission().stream()
													 .map(perms -> new SimpleGrantedAuthority(perms.getPermission()))
													 .collect(Collectors.toSet());
		permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permission;
	}
}
