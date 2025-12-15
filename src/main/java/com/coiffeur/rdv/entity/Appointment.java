package com.coiffeur.rdv.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appointmentId;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private String status;
	private String note;
	
	public Appointment() {}
	
	
	public Appointment(LocalDateTime startAtLocalDateTime, LocalDateTime endAt, String status, String note) {
		
		this.startAt = startAt;
		this.endAt = endAt;
		this.status = status;
		this.note = note;
	}
	
	public Appointment(Long appointmentId, LocalDateTime startAtLocalDateTime, LocalDateTime endAt, String status, String note) {
		
		this.appointmentId = appointmentId;
		this.startAt = startAt;
		this.endAt = endAt;
		this.status = status;
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


	public LocalDateTime getEndAt() {
		return endAt;
	}


	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
	
}
