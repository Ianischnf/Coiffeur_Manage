package com.coiffeur.rdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.coiffeur.rdv.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByEmail(String email);
}
