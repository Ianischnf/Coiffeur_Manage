package com.coiffeur.rdv.repository;

import com.coiffeur.rdv.entity.HairDresser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HairDresserRepository extends JpaRepository<HairDresser, Long> {
    Optional<HairDresser> findByEmail(String email);
}
