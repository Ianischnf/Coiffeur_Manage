package com.coiffeur.rdv.service;

import java.util.List;

import com.coiffeur.rdv.dto.AppointmentRequest;
import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.entity.AppointmentStatus;

public interface AppointmentService {

	Appointment saveAppointment(AppointmentRequest req);
	List<Appointment> fetchAllAppointment();
	Appointment updateAppointment(Appointment appointment, Long appointmentId);
	void deleteAppointment(Long appointmentId);

	Appointment acceptAppointment(Long appointmentId, Long HairDresserId);
	Appointment rejectAppointment(Long appointmentId, Long HairDresserId);

	// ✅ ta méthode existante (on la garde)
	List<Appointment> findByHairdresser_IdAndStatus(Long hairdresserId);

	// ✅ AJOUT : même chose mais avec status paramétrable
	List<Appointment> findByHairdresser_IdAndStatus(Long hairdresserId, AppointmentStatus status);

	// ✅ AJOUT (optionnel mais pratique) : récupérer tous les RDV d’un coiffeur (sans filtre)
	List<Appointment> findByHairdresser_Id(Long hairdresserId);
}
