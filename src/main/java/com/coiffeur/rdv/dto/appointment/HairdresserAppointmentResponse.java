package com.coiffeur.rdv.dto.appointment;

import com.coiffeur.rdv.entity.AppointmentStatus;

import java.time.LocalDateTime;

public record HairdresserAppointmentResponse(
        Long appointmentId,
        LocalDateTime startAt,
        String note,
        AppointmentStatus status,

        Long clientId,
        String ClientFullName
) {
}
