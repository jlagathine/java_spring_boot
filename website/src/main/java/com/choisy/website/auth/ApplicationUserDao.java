package com.choisy.website.auth;

import java.util.Optional;

public interface ApplicationUserDao {

	Optional<ApplicationUser> selectApplicationUserByUsername(String username);


}
