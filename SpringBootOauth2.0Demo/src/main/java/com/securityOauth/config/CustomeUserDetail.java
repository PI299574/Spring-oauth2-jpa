package com.securityOauth.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.securityOauth.entity.Role;
import com.securityOauth.entity.User;

public class CustomeUserDetail implements UserDetails {

	private String username;
	private String password;
	Collection<? extends GrantedAuthority> authorities;

	public CustomeUserDetail(User user) {
		// TODO Auto-generated constructor stub
		this.username = user.getUsername();
		this.password = user.getPassword();
		List<GrantedAuthority> auth = new ArrayList<>();
		for (Role role : user.getRole()) {
			auth.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
		}
		this.authorities = auth;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
