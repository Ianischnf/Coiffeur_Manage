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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private AppointmentStatus status = AppointmentStatus.PENDING;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hairdresser_id", nullable = false)
	private HairDresser hairdresser;


	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Appointment() {}

	public Appointment(Long appointmentId, LocalDateTime startAt, String note, String status, HairDresser hairDresser, Client client) {
		this.appointmentId = appointmentId;
		this.startAt = startAt;
		this.note = note;
		this.hairdresser = hairDresser;
		this.client = client;
	}

	public Appointment(LocalDateTime startAt, String note, HairDresser hairDresser, Client client) {
		this.startAt = startAt;
		this.note = note;
		this.hairdresser = hairDresser;
		this.client = client;
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

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
