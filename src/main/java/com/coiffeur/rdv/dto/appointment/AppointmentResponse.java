package com.coiffeur.rdv.dto.appointment;

import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.entity.HairDresser;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long appointmentId,
        LocalDateTime startAt,
        String note,
        AppointmentStatus status,
        Long hairdresserId
) {
}
