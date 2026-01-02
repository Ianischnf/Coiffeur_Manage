package com.coiffeur.rdv.dto;

import jakarta.validation.constraints.Email;

public record HairDresserRequest(

        String FirstName,
        String LastName,
        String Email,
        String Password
) {
}
