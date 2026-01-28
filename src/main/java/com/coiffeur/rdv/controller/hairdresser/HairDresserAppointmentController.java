package com.coiffeur.rdv.controller.hairdresser;

import com.coiffeur.rdv.dto.appointment.AppointmentResponse;
import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.dto.hairdresserDTO.AcceptAppointmentDTO;
import com.coiffeur.rdv.dto.hairdresserDTO.RefuseAppointmentDTO;
import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.service.appointments.AppointmentService;
import com.coiffeur.rdv.service.Auth.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hairdresser/appointments")
public class HairDresserAppointmentController {

    private final AppointmentService appointmentService;
    private final AuthService authService;

    public HairDresserAppointmentController(AppointmentService appointmentService, AuthService authService) {
        this.appointmentService = appointmentService;
        this.authService = authService;
    }

    /**
     * Liste les RDV du coiffeur connecté
     */
    @GetMapping
    public List<HairdresserAppointmentResponse> myAppointments(@RequestParam(required = false) AppointmentStatus status) {
        return appointmentService.fetchAppointmentsForHairdresser(status);
    }

    /**
     * Accepter un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/accept")
    public AcceptAppointmentDTO accept(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return appointmentService.acceptAppointment(appointmentId, connectedHairdresserId);
    }

    /**
     * Refuser un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/reject")
    public RefuseAppointmentDTO reject(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return appointmentService.rejectAppointment(appointmentId, connectedHairdresserId);
    }
}
