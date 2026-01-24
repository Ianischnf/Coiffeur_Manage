package com.coiffeur.rdv.dto.hairdresserDTO;

public record HairDresserResponse(
        Long id,
        String FirstName,
        String LastName,
        String FullName
) {
}
