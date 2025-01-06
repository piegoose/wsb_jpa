package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Test
    public void addVisitToPatientTest() {
        // Given
        Long patientId = 8L; //pacjent w data.sql bez wizyt
        Long doctorId = 1L;
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getId()).isEqualTo(patientId);
        assertThat(patient.getVisits()).isEmpty(); //brak wizyt

        PatientEntity doctor = patientDao.findOne(doctorId);
        assertThat(doctor).isNotNull();
        assertThat(doctor.getId()).isEqualTo(doctorId);


        LocalDateTime visitTime = LocalDateTime.now();
        String visitDescription = "Follow-up consultation";
        List<String> treatmentTypes = Arrays.asList("EKG", "REHABILITATION");

        // When: Dodajemy wizytę do pacjenta
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitTime, visitDescription, treatmentTypes);

        // Then
        PatientEntity updatedPatient = patientDao.findOne(patient.getId());
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getVisits()).isNotEmpty();
        assertThat(updatedPatient.getVisits().size()).isEqualTo(1); //posiada wizytę

        VisitEntity visit = updatedPatient.getVisits().get(0); //Sprawdzenie czy wizyta ma parametry
        assertThat(visit.getDescription()).isEqualTo(visitDescription);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(visit.getTime()).isEqualTo(visitTime);
    }
}