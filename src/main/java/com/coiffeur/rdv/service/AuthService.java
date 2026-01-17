package com.coiffeur.rdv.service;

import com.coiffeur.rdv.dto.LoginRequest;
import com.coiffeur.rdv.dto.LoginResponse;
import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;

public interface AuthService {
	
    Client register(RegisterRequest req);
    LoginResponse login(LoginRequest req);
    LoginResponse loginHairdresser(LoginRequest req);
    Long getCurrentHairdresserId();
    Long getCurrentClientId();
}
