package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientService {
    PatientTO findById(Long id);
    PatientTO save(PatientTO patientTO);
    PatientTO update(PatientTO patientTO);
    boolean delete(Long id);
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes);
    void deletePatient(Long patientId);
}
