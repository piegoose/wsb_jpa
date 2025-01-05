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
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("johndoe@example.com");
        patient.setTelephoneNumber("123456789");
        patient.setHasInsurence(true);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setPatientNumber("P123");
        patient = patientDao.save(patient);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Dr");
        doctor.setLastName("Smith");
        doctor.setSpecialization(Specialization.NEUROLOGIST);
        doctor.setTelephoneNumber("987654321");
        doctor.setDoctorNumber("D123");
        doctor = entityManager.merge(doctor);

        LocalDateTime visitTime = LocalDateTime.now();
        String visitDescription = "Follow-up consultation";
        List<String> treatmentTypes = Arrays.asList("ECG", "Blood Test");

        // When: Dodajemy wizytę do pacjenta
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitTime, visitDescription, treatmentTypes);

        // Then
        PatientEntity updatedPatient = patientDao.findOne(patient.getId());
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getVisits()).isNotEmpty();
        assertThat(updatedPatient.getVisits().size()).isEqualTo(1);

        VisitEntity visit = updatedPatient.getVisits().get(0);
        assertThat(visit.getDescription()).isEqualTo(visitDescription);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(visit.getTime()).isEqualTo(visitTime);
    }
}