package com.coiffeur.rdv.service.appointments;

import java.util.List;
import java.util.Objects;

import com.coiffeur.rdv.dto.appointment.AppointmentRequest;
import com.coiffeur.rdv.dto.appointment.AppointmentResponse;
import com.coiffeur.rdv.dto.appointment.ClientAppointmentResponse;
import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.dto.hairdresserDTO.AcceptAppointmentDTO;
import com.coiffeur.rdv.dto.hairdresserDTO.RefuseAppointmentDTO;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.entity.Client;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.ClientRepository;
import com.coiffeur.rdv.repository.HairDresserRepository;
import com.coiffeur.rdv.service.Auth.AuthService;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.repository.AppointmentRepository;

import static java.util.Arrays.stream;

@Service
public class AppointmentServiceImpl implements AppointmentService {

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

	// MANAGER APPOINTMENT FOR CLIENT
	@Override
	public AppointmentResponse saveAppointment(AppointmentRequest req) {

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

		Appointment savedAppointment = appointmentRepository.save(appointment);

		return new AppointmentResponse(
				savedAppointment.getAppointmentId(),
				savedAppointment.getStartAt(),
				savedAppointment.getNote(),
				savedAppointment.getStatus(),
				savedAppointment.getHairdresser().getId()

		);


	}

	//recuperation rdv des clients
	@Override
	public List<ClientAppointmentResponse> fetchAllAppointmentForClient() {
		Long clientId = authService.getCurrentClientId();


		return appointmentRepository.findByClient_ClientId(clientId)
				.stream() //Transforme en flux de données pour effectué des oppérations
				.map(appointment -> new ClientAppointmentResponse(
						appointment.getAppointmentId(),
						appointment.getStartAt(),
						appointment.getNote(),
						appointment.getStatus(),
						appointment.getHairdresser().getId(),
						appointment.getHairdresser().getFirstName() + " " +
								appointment.getHairdresser().getLastName()
				))
				.toList();
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
		appointmentRepository.deleteById(appointmentId);

	}


	// MANAGE APPOINTMENTS FOR HAIRDRESSER
	@Override
	public AcceptAppointmentDTO acceptAppointment(Long appointmentId, Long HairDresserId) {

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
		Appointment savedAppointmentStatus = appointmentRepository.save(appointment);

		return new AcceptAppointmentDTO(
				savedAppointmentStatus.getAppointmentId(),
				savedAppointmentStatus.getStatus()
		);
	}

	@Override
	public RefuseAppointmentDTO rejectAppointment(Long appointmentId, Long HairDresserId) {

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("RDV introuvable"));

		Long appointmentHairdresserId = appointment.getHairdresser().getId();

		if (!appointmentHairdresserId.equals(HairDresserId)) {
			throw new RuntimeException("Accès interdit : ce RDV ne vous appartient pas");
		}

		if (appointment.getStatus() != AppointmentStatus.PENDING) {
			throw new RuntimeException("RDV déjà traité");
		}

		appointment.setStatus(AppointmentStatus.REFUSED);
		Appointment savedAppointmentReject = appointmentRepository.save(appointment);

		return new RefuseAppointmentDTO(
				savedAppointmentReject.getAppointmentId(),
				savedAppointmentReject.getStatus()
		);

	}


	// recuperation rdv pour coiffeur
	@Override
	public List<HairdresserAppointmentResponse> fetchAppointmentsForHairdresser(AppointmentStatus status) {

		Long hairdresserId = authService.getCurrentHairdresserId();
		List<Appointment> appointments; //Liste des rdv


		// CHOIX DE LA LISTE A UTILISER DU REPOSITORY
		if(status == null){
			appointments = appointmentRepository.findByHairdresser_Id(hairdresserId);
		} else {
			appointments = appointmentRepository.findByHairdresser_IdAndStatus(hairdresserId, status);
		}

		return appointments.stream()
				.map(appointment -> new HairdresserAppointmentResponse(
						appointment.getAppointmentId(),
						appointment.getStartAt(),
						appointment.getNote(),
						appointment.getStatus(),
						appointment.getClient().getClientId(),
						appointment.getClient().getFirstName() + " " + appointment.getClient().getLastName()
				))
				.toList();
	}


}
