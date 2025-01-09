package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    @Transactional
    void testFindBySurnameQuery() {
        //Given
        String lastName = "Doe";
        //When
        List<PatientEntity> patients = patientDao.findBySurnameQuery(lastName);
        //Then
        assertNotNull(patients);
        assertThat(patients).allSatisfy(patient -> assertThat(patient.getLastName()).isEqualTo(lastName));
    }


    @Test
    @Transactional
    void findVisitsByPatientIdQuery() {
        //Given
        Long patientId = 1L;
        //When
        List<VisitEntity> visits = patientDao.findVisitsByPatientIdQuery(patientId);
        //Then
        assertThat(visits).isNotEmpty();
        visits.forEach(visit-> {
            assertThat(visit.getPatient().getId()).isEqualTo(patientId);
        });
    }

    @Test
    @Transactional
    void findPatientsWithMoreThanXVisitsQuery() {
        //Given
        Long numberOfVisits = 2L;
        //When
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisitsQuery(numberOfVisits);
        //Then
        patients.forEach(patient -> System.out.println(
                "Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                        ", Visits: " + patient.getVisits().size()));
        assertThat(patients).isNotEmpty();
        patients.forEach(patient -> {
            assertThat(patient.getVisits().size()).isGreaterThan(numberOfVisits.intValue());
        });


    }

    @Test
    @Transactional
    void findPatientsByInsuranceQuery() {
        //Given
        Boolean insurance = Boolean.TRUE;
        //When
        List<PatientEntity> patients = patientDao.findPatientsByInsuranceQuery(insurance);
        //Then
        assertThat(patients).isNotEmpty();
        patients.forEach(patient -> {
            assertThat(patient.getHasInsurence()).isEqualTo(insurance);
        });
        patients.forEach(patient -> System.out.println(
                "Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                        ", Has Insurance: " + patient.getHasInsurence()));
    }
}