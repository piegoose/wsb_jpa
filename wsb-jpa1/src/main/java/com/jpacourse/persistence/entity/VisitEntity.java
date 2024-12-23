package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient; // Relacja jednostronna od strony dziecka (VisitEntity jako właściciel relacji)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor; // Relacja jednostronna od strony dziecka (VisitEntity jako właściciel relacji)

	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MedicalTreatmentEntity> medicalTreatment; // Relacja dwustronna, rodzicem jest MedicalTreatmentEntity

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public List<MedicalTreatmentEntity> getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public void setMedicalTreatment(List<MedicalTreatmentEntity> medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}
}
