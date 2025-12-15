package com.coiffeur.rdv.dto;

public record RegisterRequest(
        String firstName,
        String lastName,
        String phone,
        String email,
        String password
) {}
