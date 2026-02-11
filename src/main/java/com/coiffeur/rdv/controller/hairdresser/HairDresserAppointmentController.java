package com.coiffeur.rdv.controller.hairdresser;

import com.coiffeur.rdv.dto.appointment.HairdresserAppointmentResponse;
import com.coiffeur.rdv.dto.hairdresserDTO.AcceptAppointmentDTO;
import com.coiffeur.rdv.dto.hairdresserDTO.RefuseAppointmentDTO;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.service.ClientAppointmentService.ClientAppointmentService;
import com.coiffeur.rdv.service.Auth.AuthService;
import com.coiffeur.rdv.service.HairdresserAppointmentService.HairdresserAppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hairdresser/appointments")
public class HairDresserAppointmentController {

    private final HairdresserAppointmentService hairdresserAppointmentService;
    private final AuthService authService;

    public HairDresserAppointmentController(HairdresserAppointmentService hairdresserAppointmentService, AuthService authService) {
        this.hairdresserAppointmentService = hairdresserAppointmentService;
        this.authService = authService;
    }

    /**
     * Liste les RDV du coiffeur connecté
     */
    @GetMapping
    public List<HairdresserAppointmentResponse> myAppointments(@RequestParam(required = false) AppointmentStatus status) {
        return hairdresserAppointmentService.fetchAppointmentsForHairdresser(status);
    }

    /**
     * Accepter un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/accept")
    public AcceptAppointmentDTO accept(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return hairdresserAppointmentService.acceptAppointment(appointmentId, connectedHairdresserId);
    }

    /**
     * Refuser un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/reject")
    public RefuseAppointmentDTO reject(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return hairdresserAppointmentService.rejectAppointment(appointmentId, connectedHairdresserId);
    }
}
