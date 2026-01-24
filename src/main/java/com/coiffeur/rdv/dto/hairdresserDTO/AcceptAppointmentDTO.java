package com.coiffeur.rdv.dto.hairdresserDTO;

import com.coiffeur.rdv.entity.AppointmentStatus;

import java.time.LocalDateTime;

public record AcceptAppointmentDTO(

        Long appointmentId,
        AppointmentStatus status
) {
}
