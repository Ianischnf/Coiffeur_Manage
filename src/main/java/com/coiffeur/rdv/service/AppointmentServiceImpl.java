package com.coiffeur.rdv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.coiffeur.rdv.dto.AppointmentRequest;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.ClientRepository;
import com.coiffeur.rdv.repository.HairDresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	private final AppointmentRepository appointmentRepository;
	private final HairDresserRepository hairDresserRepository;
	private final ClientRepository clientRepository;
	private final AuthService authService;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, HairDresserRepository hairDresserRepository, ClientRepository clientRepository, AuthService authService){
		this.appointmentRepository = appointmentRepository;
		this.hairDresserRepository = hairDresserRepository;
		this.clientRepository = clientRepository;
		this.authService = authService;
	}

	@Override
	public Appointment saveAppointment(AppointmentRequest req) {

		HairDresser hairdresser = hairDresserRepository
				.findById(req.hairdresserId())
				.orElseThrow(() -> new RuntimeException(
						"Hairdresser not found with id " + req.hairdresserId()
				));

		Long connectedClientId = authService.getCurrentClientId();
		System.out.println("id client connecté : " + connectedClientId);

		Client client = clientRepository
				.findById(connectedClientId)
				.orElseThrow(() -> new RuntimeException(
						"Client not found with id " + connectedClientId
				));


		Appointment appointment = new Appointment(
				req.startAt(),
				req.note(),
				hairdresser,
				client
		);

		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> fetchAllAppointment() {

		return (List<Appointment>)
				appointmentRepository.findAll();
	}

	@Override
	public Appointment updateAppointment(Appointment appointment, Long appointmentId) {

		Appointment appDB = appointmentRepository.findById(appointmentId).get();

		if (Objects.nonNull(appointment.getStartAt())) {
			appDB.setStartAt(appointment.getStartAt());
		}

		if (Objects.nonNull(appointment.getNote())
		        && !"".equalsIgnoreCase(appointment.getNote())) {

			appDB.setNote(appointment.getNote());
		}

		return appointmentRepository.save(appDB);
	}

	@Override
	public void deleteAppointment(Long appointmentId) {

	}

	@Override
	public Appointment acceptAppointment(Long appointmentId, Long HairDresserId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("RDV introuvable"));

		// Vérifie que le RDV appartient bien à ce coiffeur
		Long appointmentHairdresserId = appointment.getHairdresser().getId();

		if (!appointmentHairdresserId.equals(HairDresserId)) {
			throw new RuntimeException("Accès interdit : ce RDV ne vous appartient pas");
		}

		// Vérifie le status
		if (appointment.getStatus() != AppointmentStatus.PENDING) {
			throw new RuntimeException("RDV déjà traité");
		}

		appointment.setStatus(AppointmentStatus.ACCEPTED);
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment rejectAppointment(Long appointmentId, Long HairDresserId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("RDV introuvable"));

		Long appointmentHairdresserId = appointment.getHairdresser().getId();
		// si pas getId(): .getHairDresserId()

		if (!appointmentHairdresserId.equals(HairDresserId)) {
			throw new RuntimeException("Accès interdit : ce RDV ne vous appartient pas");
		}

		if (appointment.getStatus() != AppointmentStatus.PENDING) {
			throw new RuntimeException("RDV déjà traité");
		}

		appointment.setStatus(AppointmentStatus.REJECTED);
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> findByHairdresser_IdAndStatus(Long hairdresserId) {
		return appointmentRepository.findByHairdresser_IdAndStatus(hairdresserId, AppointmentStatus.PENDING);
	}

	/**
	 * ✅ surcharge
	 */
	@Override
	public List<Appointment> findByHairdresser_IdAndStatus(Long hairdresserId, AppointmentStatus status) {
		return appointmentRepository.findByHairdresser_IdAndStatus(hairdresserId, status);
	}

	@Override
	public List<Appointment> findByHairdresser_Id(Long hairdresserId) {
		return appointmentRepository.findByHairdresser_Id(hairdresserId);
	}



}
