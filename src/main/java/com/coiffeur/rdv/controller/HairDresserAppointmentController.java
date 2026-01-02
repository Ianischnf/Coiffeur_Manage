package com.coiffeur.rdv.controller;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.entity.AppointmentStatus;
import com.coiffeur.rdv.service.AppointmentService;
import com.coiffeur.rdv.service.AuthService;
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
     * - /hairdresser/appointments -> tous
     * - /hairdresser/appointments?status=PENDING -> filtré
     */
    @GetMapping
    public List<Appointment> myAppointments(@RequestParam(required = false) AppointmentStatus status) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();

        if (status == null) {
            return appointmentService.findByHairdresser_Id(connectedHairdresserId);
        }
        return appointmentService.findByHairdresser_IdAndStatus(connectedHairdresserId, status);
    }

    /**
     * Accepter un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/accept")
    public Appointment accept(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return appointmentService.acceptAppointment(appointmentId, connectedHairdresserId);
    }

    /**
     * Refuser un RDV (uniquement si c'est le coiffeur propriétaire)
     */
    @PatchMapping("/{id}/reject")
    public Appointment reject(@PathVariable("id") Long appointmentId) {
        Long connectedHairdresserId = authService.getCurrentHairdresserId();
        return appointmentService.rejectAppointment(appointmentId, connectedHairdresserId);
    }
}
