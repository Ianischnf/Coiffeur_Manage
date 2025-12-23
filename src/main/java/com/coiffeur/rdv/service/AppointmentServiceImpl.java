package com.coiffeur.rdv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.coiffeur.rdv.dto.AppointmentRequest;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.HairDresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	private final AppointmentRepository appointmentRepository;
	private final HairDresserRepository hairDresserRepository;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, HairDresserRepository hairDresserRepository){
		this.appointmentRepository = appointmentRepository;
		this.hairDresserRepository = hairDresserRepository;
	}

	@Override
	public Appointment saveAppointment(AppointmentRequest req) {

		HairDresser hairdresser = hairDresserRepository
				.findById(req.hairdresserId())
				.orElseThrow(() -> new RuntimeException(
						"Hairdresser not found with id " + req.hairdresserId()
				));


		Appointment appointment = new Appointment(
				req.startAt(),
				req.note(),
				hairdresser
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
		
		appointmentRepository.deleteById(appointmentId);
	}

}
