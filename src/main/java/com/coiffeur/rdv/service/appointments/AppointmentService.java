package com.coiffeur.rdv.service.appointments;

import java.util.List;

import com.coiffeur.rdv.dto.AppointmentRequest;
import com.coiffeur.rdv.dto.appointment.ClientAppointmentResponse;
import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.entity.AppointmentStatus;

public interface AppointmentService {

	//GESTION RDV CLIENT
	Appointment saveAppointment(AppointmentRequest req);
	List<ClientAppointmentResponse> fetchAllAppointmentForClient();
	Appointment updateAppointment(Appointment appointment, Long appointmentId);
	void deleteAppointment(Long appointmentId);


	//GESTION RDV COIFFEUR
	Appointment acceptAppointment(Long appointmentId, Long HairDresserId);
	Appointment rejectAppointment(Long appointmentId, Long HairDresserId);

	//SURCHAGE POUR FILTRER LES RDV VIA STATUS
	List<HairdresserAppointmentResponse> fetchAppointmentsForHairdresser(AppointmentStatus status);
}
