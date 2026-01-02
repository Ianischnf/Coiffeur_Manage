package com.coiffeur.rdv.service;

import java.time.LocalDateTime;

import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.HairDresserRepository;
import com.coiffeur.rdv.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.dto.LoginRequest;
import com.coiffeur.rdv.dto.LoginResponse;
import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.repository.ClientRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Service
public class AuthServiceImpl implements AuthService {

	private final ClientRepository clientRepository;
	private final HairDresserRepository hairDresserRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	public AuthServiceImpl(ClientRepository clientRepository, HairDresserRepository hairDresserRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.clientRepository = clientRepository;
		this.hairDresserRepository = hairDresserRepository;
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

	@Override
	public LoginResponse loginHairdresser(LoginRequest req) {
		HairDresser hairdresser = hairDresserRepository.findByEmail(req.email())
				.orElseThrow(() -> new RuntimeException("Email inconnu"));

		if (!passwordEncoder.matches(req.password(), hairdresser.getPassword())) {
			throw new RuntimeException("Mot de passe incorrect");
		}

		String token = jwtService.generateToken(hairdresser.getEmail());

		return new LoginResponse(token);
	}

	@Override
	public Long getCurrentHairdresserId() {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !auth.isAuthenticated()){
			throw new RuntimeException("Non authentifié");
		}

		String email = auth.getName();
		HairDresser hairDresser = hairDresserRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Coiffeur introuvable"));

		return  hairDresser.getId();
	}
}
