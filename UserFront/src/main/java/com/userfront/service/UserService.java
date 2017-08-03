package com.userfront.service;

import java.util.Set;

/**	Implemented by the class: UserServiceImpl */

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

public interface UserService {
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	boolean checkUserExists(String username, String email);
	
	boolean checkUsernameExists(String username);
	
	boolean checkEmailExists(String email);
	
	void save (User user);
	
	User createUser(User user, Set<UserRole> userRoles);
}
