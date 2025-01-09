package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitDao extends Dao<VisitEntity, Long> {
    List<VisitEntity> findByPatientId(Long patientId);
//    VisitEntity createVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes);
}
