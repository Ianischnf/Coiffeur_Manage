package com.coiffeur.rdv.service.ClientAppointmentService;

import java.util.List;

import com.coiffeur.rdv.dto.appointment.AppointmentRequest;
import com.coiffeur.rdv.dto.appointment.AppointmentResponse;
import com.coiffeur.rdv.dto.appointment.ClientAppointmentResponse;
import com.coiffeur.rdv.entity.Appointment;

public interface ClientAppointmentService {

	//GESTION RDV CLIENT
	AppointmentResponse saveAppointment(AppointmentRequest req);
	List<ClientAppointmentResponse> fetchAllAppointmentForClient();
	Appointment updateAppointment(Appointment appointment, Long appointmentId);
	void deleteAppointment(Long appointmentId);
}
