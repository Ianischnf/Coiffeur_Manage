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

	@Column (name = "note")
	private String note;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hairdresser_id", nullable = false)
	private HairDresser hairdresser;

	public Appointment() {}

	public Appointment(Long appointmentId, LocalDateTime startAt, String note, HairDresser hairDresser) {
		this.appointmentId = appointmentId;
		this.startAt = startAt;
		this.note = note;
		this.hairdresser = hairDresser;
	}

	public Appointment(LocalDateTime startAt, String note, HairDresser hairDresser) {
		this.startAt = startAt;
		this.note = note;
		this.hairdresser = hairDresser;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public HairDresser getHairdresser() {
		return hairdresser;
	}

	public void setHairdresser(HairDresser hairdresser) {
		this.hairdresser = hairdresser;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
