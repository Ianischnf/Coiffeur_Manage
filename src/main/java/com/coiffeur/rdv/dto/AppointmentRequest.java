package com.coiffeur.rdv.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(

        LocalDateTime startAt,
        String note,
        Long hairdresserId,
        Long clientId
) {
}
