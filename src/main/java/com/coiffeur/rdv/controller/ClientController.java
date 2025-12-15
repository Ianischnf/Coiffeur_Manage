package com.coiffeur.rdv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coiffeur.rdv.dto.RegisterRequest;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired 
	private ClientService clientService;
	
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	
	@GetMapping
	public List<Client> fetchClientList(){
		return clientService.fetchClientList();
	}
	
	@PutMapping("/{id}")
	public Client clientUpdate(@RequestBody Client client, @PathVariable("id") Long clientId) {
		return clientService.updateClient(client, clientId); 
	}
	
	@DeleteMapping("/{id}")
	public void deleteClientById(@PathVariable("id") Long clientId) {
		
		clientService.deleteClientById(clientId);
	}
	
}
