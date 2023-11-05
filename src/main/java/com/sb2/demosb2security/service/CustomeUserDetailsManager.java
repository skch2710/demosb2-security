package com.sb2.demosb2security.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.sb2.demosb2security.dao.EmployeeDAO;
import com.sb2.demosb2security.model.Employee;

@Service
public class CustomeUserDetailsManager implements UserDetailsManager{

	@Autowired
	private EmployeeDAO userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = userRepository.findByEmailIdIgnoreCase(username);
		if (!user.getEmailId().equals(username)) {
			throw new UsernameNotFoundException("Access Denied");
		}
		Collection<GrantedAuthority> authoriies = new HashSet<>();
		authoriies.add(new SimpleGrantedAuthority(user.getEmployeeRole().getRole().getRoleName()));
		
		User ur = new User(user.getEmailId(), user.getPasswordSalt(), authoriies);
		
		return ur;
	}

	@Override
	public void createUser(UserDetails user) {
	}

	@Override
	public void updateUser(UserDetails user) {
	}

	@Override
	public void deleteUser(String username) {
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
	}

	@Override
	public boolean userExists(String username) {
		Employee user = userRepository.findByEmailIdIgnoreCase(username);
		if (user.getEmailId().equals(username)) {
			return true;
		}
		return false;
	}

}
