package com.coiffeur.rdv.dto.appointment;

import java.time.LocalDateTime;

public record AppointmentRequest(

        LocalDateTime startAt,
        String note,
        Long hairdresserId
) {
}
