package com.coiffeur.rdv.service.HairdresserAppointmentService;

import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.dto.hairdresserDTO.AcceptAppointmentDTO;
import com.coiffeur.rdv.dto.hairdresserDTO.RefuseAppointmentDTO;
import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.repository.AppointmentRepository;
import com.coiffeur.rdv.service.Auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HairdresserAppointmentServiceImpl implements HairdresserAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AuthService authService;

    public HairdresserAppointmentServiceImpl(AppointmentRepository appointmentRepository, AuthService authService) {
        this.appointmentRepository = appointmentRepository;
        this.authService = authService;
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
