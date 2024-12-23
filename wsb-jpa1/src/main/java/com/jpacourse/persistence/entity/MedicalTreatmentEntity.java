package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.TreatmentType;

import javax.persistence.*;

import javax.persistence.*;
@Entity
@Table(name = "MEDICAL_TREATMENT")
public class MedicalTreatmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private TreatmentType type;

	@ManyToOne
	private VisitEntity visit; // Relacja jednostronna od strony dziecka (MedicalTreatmentEntity jako właściciel relacji)

	public void setVisit(VisitEntity visit) {
		this.visit = visit;
	}
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

	public TreatmentType getType() {
		return type;
	}

	public void setType(TreatmentType type) {
		this.type = type;
	}

}
