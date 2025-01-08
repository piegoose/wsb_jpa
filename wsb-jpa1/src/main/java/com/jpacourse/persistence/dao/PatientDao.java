package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    List<PatientEntity> findBySurnameQuery(String lastName);
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes);
    List<VisitEntity> findVisitsByPatientIdQuery(Long patientId);
    List<PatientEntity> findPatientsWithMoreThanXVisitsQuery(Long numberOfVisits);
    List<PatientEntity> findPatientsByInsuranceQuery(Boolean hasInsurence);
}
