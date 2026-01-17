package com.coiffeur.rdv.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coiffeur.rdv.dto.LoginRequest;
import com.coiffeur.rdv.dto.LoginResponse;
import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.service.Auth.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("client/register")
	 Client register(@Valid @RequestBody RegisterRequest req) {
		return authService.register(req);
	}
	
	@PostMapping("client/login")
	LoginResponse login(@Valid @RequestBody LoginRequest req) {
		return authService.login(req);
	}

	@PostMapping("/hairdresser/login")
	LoginResponse loginHairdresser(@Valid @RequestBody LoginRequest req){ return authService.loginHairdresser(req); }
}
