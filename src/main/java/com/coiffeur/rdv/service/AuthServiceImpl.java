package com.coiffeur.rdv.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.dto.LoginRequest;
import com.coiffeur.rdv.dto.LoginResponse;
import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.repository.ClientRepository;


@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	
	public AuthServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.clientRepository = clientRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Override
	public Client register(RegisterRequest req) {
		
		if(clientRepository.findByEmail(req.email()).isPresent()) {
			throw new RuntimeException("Email déjà utilisée");
		}
		
		Client client = new Client(
		
				req.firstName(),
				req.lastName(),
				req.phone(),
				req.email(),
				passwordEncoder.encode(req.password()),
				LocalDateTime.now()
		
		);
				
		return clientRepository.save(client);
	}
	
	@Override
	public LoginResponse login(LoginRequest req) {

	    // 1️⃣ chercher le client par email
	    Client client = clientRepository.findByEmail(req.email())
	            .orElseThrow(() -> new RuntimeException("Email inconnu"));

	    // 2️⃣ vérifier le mot de passe
	    if (!passwordEncoder.matches(req.password(), client.getPassword())) {
	        throw new RuntimeException("Mot de passe incorrect");
	    }

	    // 3️⃣ générer le JWT (avec l’email)
	    String token = jwtService.generateToken(client.getEmail());

	    // 4️⃣ renvoyer le token
	    return new LoginResponse(token);
	}


}
