package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {
    @Override
    public List<VisitEntity> findByPatientId(Long patientId) {
        return entityManager.createNativeQuery("SELECT * FROM VISIT v JOIN PATIENT p ON v.patient_id = p.id and v.patient_id = :patientId", VisitEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

//    @Override
//    @Transactional
//    public VisitEntity createVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes) {
//
//        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
//        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
//        if (patient == null || doctor == null) {
//            throw new IllegalArgumentException("Invalid patientId: " + patientId + " or doctorId: " + doctorId);
//        }
//        VisitEntity visitEntity = new VisitEntity();
//        visitEntity.setPatient(patient);
//        visitEntity.setDoctor(doctor);
//
//    }
}
