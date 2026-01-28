package com.coiffeur.rdv.dto.hairdresserDTO;

import com.coiffeur.rdv.entity.AppointmentStatus;

public record RefuseAppointmentDTO(
        Long appointmentId,
        AppointmentStatus status
) {
}
