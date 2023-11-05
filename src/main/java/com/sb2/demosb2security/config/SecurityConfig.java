package com.sb2.demosb2security.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
//this annotation will enable method level security
public class SecurityConfig{
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		Collection<GrantedAuthority> authorities1 = Arrays.asList(
			    new SimpleGrantedAuthority("USER"),
			    new SimpleGrantedAuthority("ADMIN"));
		
		User ur1 = new User("skch2710@gmail.com", passwordEncoder().encode("S@thi$+b27"), authorities);
		User ur2 = new User("my-user", passwordEncoder().encode("password"), authorities1);
		
//		UserDetails user1 = User.withUsername("skch2710@gmail.com").password(passwordEncoder().encode("S@thi$+b27")).roles("USER").build();
//		UserDetails user2 = User.withUsername("my-user").password(passwordEncoder().encode("password")).roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(ur1,ur2);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .antMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().and().httpBasic()
            .and().csrf().disable(); // Disable CSRF protection
		return http.build();
	}	
}
