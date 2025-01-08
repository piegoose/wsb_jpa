package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private PatientDao patientDao;


    @Test
    @Transactional
    public void testFindPatientByIdShouldReturnCorrectTO() {
        // given
        Long patientId = 1L; // ID istniejącego pacjenta w danych testowych

        // when
        PatientTO patient = patientService.findById(patientId);

        // then
        assertThat(patient).isNotNull();
        assertThat(patient.getId()).isEqualTo(patientId);
        assertThat(patient.getFirstName()).isNotNull();
        assertThat(patient.getLastName()).isNotNull();
        assertThat(patient.getTelephoneNumber()).isNotNull();
        assertThat(patient.getEmail()).isNotNull();
        assertThat(patient.getPatientNumber()).isNotNull();
        assertThat(patient.getDateOfBirth()).isNotNull();
        assertThat(patient.getHasInsurence()).isNotNull();

        assertThat(patient.getVisits()).isNotNull();
        assertThat(patient.getVisits()).allSatisfy(visit -> {
            assertThat(visit.getId()).isNotNull();
            assertThat(visit.getVisitDate()).isNotNull();
            assertThat(visit.getDescription()).isNotNull();
            assertThat(visit.getDoctor_id()).isNotNull();
            assertThat(visit.getTreatmentTypes()).isNotNull();
            assertThat(visit.getTreatmentTypes()).isNotEmpty();
        });
    }


    @Transactional
    @Test
    public void testDeletePatientShouldRemoveVisits() {

        // given
        Long patientId = 1L;
        Long visitId = 1L;
        Long doctorId = 1L;

        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotEmpty();

        System.out.println("Name: " + patient.getLastName());
        VisitEntity visit = visitDao.findOne(visitId);
        assertThat(visit).isNotNull();
        assertThat(visit.getPatient().getId()).isEqualTo(patientId);

        // when
        patientService.deletePatient(patientId);

        // then
        PatientEntity deletedPatient = patientDao.findOne(patientId);
        assertThat(deletedPatient).isNull();

        List<VisitEntity> remainingVisits = visitDao.findAll();
        assertThat(remainingVisits).noneMatch(v -> v.getPatient().getId().equals(patientId));

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        assertThat(doctor).isNotNull();
    }

}