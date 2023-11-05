package com.sb2.demosb2security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/test/")
public class TestController {

	@GetMapping("/test-admin")
	@Operation(security = @SecurityRequirement(name = "basicAuth"))
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<?> testUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getAuthorities());
		return ResponseEntity.ok("Test admin only");
	}

	@GetMapping("/test-user")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasAnyAuthority('USER')")
	public ResponseEntity<?> testAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getAuthorities());
		return ResponseEntity.ok("Test user and admin");
	}
}
