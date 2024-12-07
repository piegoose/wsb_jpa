package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
    PatientTO findById(Long id);
    PatientTO save(PatientTO patientTO);
    PatientTO update(PatientTO patientTO);
    boolean delete(Long id);
}
