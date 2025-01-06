package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
//    @Override
//    public List<PatientEntity> findByLastName(String lastName) {
//        return entityManager.createNativeQuery("SELECT * FROM patient WHERE last_name = ?", PatientEntity.class)
//                .setParameter(1, lastName)
//                .getResultList();
//    }

    @Override
    @Transactional
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes) {
        PatientEntity patient = findOne(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient id not found: " + patientId);
        }
        DoctorEntity doctor = findEntityById(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor id not found: " + doctorId);
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patient);
        visitEntity.setDoctor(doctor);
        visitEntity.setTime(visitDate);
        visitEntity.setDescription(description);

        List<MedicalTreatmentEntity> treatments = treatmentTypes.stream().map(type -> {
            MedicalTreatmentEntity treatment = new MedicalTreatmentEntity();
            treatment.setDescription(type);
            treatment.setVisit(visitEntity);
            treatment.setType(TreatmentType.valueOf(type));
            return treatment;
        }).collect(Collectors.toList());

        visitEntity.setMedicalTreatment(treatments); //mialem skasowac, ale brakowalo tylko 1 linijki

        patient.getVisits().add(visitEntity);

        update(patient);
    }

}
