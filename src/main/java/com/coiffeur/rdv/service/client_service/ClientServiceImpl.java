package com.coiffeur.rdv.service.client_service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	
	@Override
	public List<Client> fetchClientList() {
		return (List<Client>)
				clientRepository.findAll();
	}

	@Override
	public Client updateClient(Client client, Long clientId) {
		Client clientDB = clientRepository.findById(clientId).get();
		
		if (Objects.nonNull(client.getFirstName())
		        && !"".equalsIgnoreCase(client.getFirstName())) {

		    clientDB.setFirstName(client.getFirstName());
		}
		
		if(Objects.nonNull(client.getLastName())
				&& !"".equalsIgnoreCase(client.getLastName())) {
			clientDB.setLastName(client.getLastName());
		}
		
		if(Objects.nonNull(client.getPhone())
				&& !"".equalsIgnoreCase(client.getPhone())) {
			clientDB.setPhone(client.getPhone());
		}
		
		if(Objects.nonNull(client.getEmail())
				&& !"".equalsIgnoreCase(client.getEmail())) {
			clientDB.setEmail(client.getEmail());
		}

		if (Objects.nonNull(client.getCreatedAt())) {
		    clientDB.setCreatedAt(client.getCreatedAt());
		}
		
		return clientRepository.save(clientDB);
	}

	@Override
	public void deleteClientById(Long ClientId) {
		clientRepository.deleteById(ClientId);
		
	}

}
