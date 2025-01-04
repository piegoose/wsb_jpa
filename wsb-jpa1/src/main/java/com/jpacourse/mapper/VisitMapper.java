package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setVisitDate(visitEntity.getTime());
        visitTO.setDescription(visitEntity.getDescription());
        if (visitEntity.getDoctor() != null){
            visitTO.setDoctor_id(visitEntity.getDoctor().getId());
        }
        if (visitEntity.getPatient() != null){
            visitTO.setPatient_id(visitEntity.getPatient().getId());
        }
        if (visitEntity.getMedicalTreatment() != null){
            List<String> medicalTreatments = visitEntity.getMedicalTreatment()
                    .stream()
                    .map(MedicalTreatmentEntity::getDescription)
                    .collect(Collectors.toList());
            visitTO.setTreatmentTypes(medicalTreatments);
        }
        return visitTO;
    }
    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;

        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setTime(visitTO.getVisitDate());
        visitEntity.setDescription(visitTO.getDescription());
        if (visitEntity.getDoctor() != null){
            visitEntity.setDoctor(new DoctorEntity());

        }
        if (visitEntity.getPatient() != null){
            visitEntity.setPatient(new PatientEntity());

        }
        if (visitEntity.getMedicalTreatment() != null){
            List<MedicalTreatmentEntity> medicalTreatments = visitTO.getTreatmentTypes()
                    .stream()
                    .map(description -> {
                        MedicalTreatmentEntity treatmentEntity = new MedicalTreatmentEntity();
                        treatmentEntity.setDescription(description);
                        treatmentEntity.setVisit(visitEntity);
                        return treatmentEntity;
                    })
                    .collect(Collectors.toList());
            visitEntity.setMedicalTreatment(medicalTreatments);
        }

        return visitEntity;
    }

}