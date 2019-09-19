package com.securityOauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.securityOauth.config.CustomeUserDetail;
import com.securityOauth.entity.Role;
import com.securityOauth.entity.User;
import com.securityOauth.repository.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public PasswordEncoder getPassworEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository)
			throws Exception {
		builder.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				if (repository.count() == 0) {
					repository.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ADMIN"))));
				}
				return new CustomeUserDetail(repository.findByUsername(username).get());
			}
		});
	}

}
