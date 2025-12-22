package com.coiffeur.rdv.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(

        LocalDateTime startAt,
        String hairdresser,
        String note
) {
}
