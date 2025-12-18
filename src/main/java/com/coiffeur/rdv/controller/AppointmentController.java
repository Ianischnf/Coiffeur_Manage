package com.coiffeur.rdv.controller;

import java.util.List;

import com.coiffeur.rdv.dto.AppointmentRequest;
import org.springframework.web.bind.annotation.*;

import com.coiffeur.rdv.entity.Appointment;
import com.coiffeur.rdv.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment saveAppointment(@RequestBody AppointmentRequest req) {
        return appointmentService.saveAppointment(req);
    }

    @GetMapping
    public List<Appointment> fetchAllAppointment() {
        return appointmentService.fetchAllAppointment();
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
