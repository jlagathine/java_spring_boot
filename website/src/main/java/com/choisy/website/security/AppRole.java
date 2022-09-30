package com.choisy.website.security;

import java.util.Set;

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
	 
}
