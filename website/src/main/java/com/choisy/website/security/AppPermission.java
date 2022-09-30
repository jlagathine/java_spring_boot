package com.choisy.website.security;

public enum AppPermission {
	USER_LECTURE("utilisateur:lecture"),
	USER_ECRITURE("utilisateur:ecriture");
	
	private final String permission;

	AppPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
