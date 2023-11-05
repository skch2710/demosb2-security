package com.sb2.demosb2security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class TestTwoController {
	
	@GetMapping("/test-get-public")
	public ResponseEntity<?> testGet() {
		return ResponseEntity.ok("Test Get public");
	}
	
	@PostMapping("/test-post-public")
	public ResponseEntity<?> testPost() {
		return ResponseEntity.ok("Test Post public");
	}

}
