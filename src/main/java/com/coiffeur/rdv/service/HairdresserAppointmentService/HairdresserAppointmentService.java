package com.coiffeur.rdv.service.HairdresserAppointmentService;

import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.dto.hairdresserDTO.AcceptAppointmentDTO;
import com.coiffeur.rdv.dto.hairdresserDTO.RefuseAppointmentDTO;
import com.coiffeur.rdv.entity.AppointmentStatus;

import java.util.List;

public interface HairdresserAppointmentService {

    //GESTION RDV COIFFEUR
    AcceptAppointmentDTO acceptAppointment(Long appointmentId, Long HairDresserId);
    RefuseAppointmentDTO rejectAppointment(Long appointmentId, Long HairDresserId);

    //SURCHAGE POUR FILTRER LES RDV VIA STATUS
    List<HairdresserAppointmentResponse> fetchAppointmentsForHairdresser(AppointmentStatus status);
}
