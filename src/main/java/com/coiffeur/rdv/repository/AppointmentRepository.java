package com.coiffeur.rdv.repository;

import org.springframework.data.repository.CrudRepository;

import com.coiffeur.rdv.entity.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long>{

}
