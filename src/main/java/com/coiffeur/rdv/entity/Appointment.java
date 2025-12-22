package com.coiffeur.rdv.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column (name = "appointment_id")
	private Long appointmentId;

	@Column (name = "start_at")
	private LocalDateTime startAt;

	@Column(name = "hair_dresser")
	private String hairDresser;

	@Column (name = "note")
	private String note;
	
	public Appointment() {}

	public Appointment(Long appointmentId, LocalDateTime startAt, String hairDresser, String note) {
		this.appointmentId = appointmentId;
		this.startAt = startAt;
		//this.hairDresser = hairDresser;
		this.note = note;
	}

	public Appointment(LocalDateTime startAt, String hairDresser, String note) {
		this.startAt = startAt;
		this.hairDresser = hairDresser;
		this.note = note;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public String getHairDresser() {
		return hairDresser;
	}

	public void setHairDresser(String hairDresser) {
		this.hairDresser = hairDresser;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
