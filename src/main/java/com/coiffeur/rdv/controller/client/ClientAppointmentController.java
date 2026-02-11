package com.coiffeur.rdv.controller.client;

import java.util.List;

import com.coiffeur.rdv.dto.appointment.AppointmentRequest;
import com.coiffeur.rdv.dto.appointment.AppointmentResponse;
import com.coiffeur.rdv.dto.appointment.ClientAppointmentResponse;
import org.springframework.web.bind.annotation.*;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.service.ClientAppointmentService.ClientAppointmentService;

@RestController
@RequestMapping("/client/appointment")
public class ClientAppointmentController {

    private final ClientAppointmentService appointmentService;

    public ClientAppointmentController(ClientAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentResponse saveAppointment(@RequestBody AppointmentRequest req) {
        return appointmentService.saveAppointment(req);
    }

    @GetMapping
    public List<ClientAppointmentResponse> fetchAllAppointment() {
        return appointmentService.fetchAllAppointmentForClient();
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@RequestBody Appointment appointment,
                                         @PathVariable("id") Long appointmentId) {
        return appointmentService.updateAppointment(appointment, appointmentId);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointmentById(@PathVariable("id") Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
