package com.coiffeur.rdv.dto.hairdresserDTO;

public record HairDresserRequest(

        String FirstName,
        String LastName,
        String Email,
        String Password
) {
}
