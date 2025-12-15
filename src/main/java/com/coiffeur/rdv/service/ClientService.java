package com.coiffeur.rdv.service;

import java.util.List;

import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;

public interface ClientService {
	
	Client saveClient (Client client);
	List<Client> fetchClientList();
	Client updateClient(Client client, Long ClientId);
	void deleteClientById(Long ClientId);
}
