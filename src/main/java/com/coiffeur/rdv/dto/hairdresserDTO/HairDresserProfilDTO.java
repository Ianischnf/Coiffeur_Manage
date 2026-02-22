package com.coiffeur.rdv.dto.hairdresserDTO;

public record HairDresserProfilDTO(
        Long HairDresserId,
        String FirstName,
        String LastName,
        String Email
) {
}
