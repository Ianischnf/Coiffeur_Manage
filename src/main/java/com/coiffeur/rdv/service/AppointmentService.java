package com.coiffeur.rdv.service;

import java.util.List;

import com.coiffeur.rdv.entity.Appointment;

public interface AppointmentService {
	
	Appointment saveAppointment(Appointment appointment);
	List<Appointment> fetchAllAppointment();
	Appointment updateAppointment(Appointment appointment, Long appointmentId);
	void deleteAppointment(Long appointmentId);
}
