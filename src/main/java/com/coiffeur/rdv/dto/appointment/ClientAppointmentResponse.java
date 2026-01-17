package com.coiffeur.rdv.dto.appointment;

import com.coiffeur.rdv.entity.AppointmentStatus;

import java.time.LocalDateTime;

public record ClientAppointmentResponse(
        Long AppointmentId,
        LocalDateTime startAt,
        String note,
        AppointmentStatus status,

        Long hairdresserId,
        String HairdresserFullName
) {
}
