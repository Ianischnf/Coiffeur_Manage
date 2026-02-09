package com.coiffeur.rdv.repository;

import com.coiffeur.rdv.entity.AppointmentStatus;
import org.springframework.data.repository.CrudRepository;

import com.coiffeur.rdv.entity.Appointment;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long>{

    List<Appointment> findByHairdresser_Id(Long hairdresserId);
    List<Appointment> findByClient_ClientId(Long clientId);
    List<Appointment> findByHairdresser_IdAndStatus(
            Long hairdresserId,
            AppointmentStatus status
    );

}
