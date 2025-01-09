package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {


    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitDao visitDao;

    @Test
 public    void shouldfindAllByPatientId() {
        //given
        long patientId = 1L;

        //when
        List<VisitTO> allByPatientId = visitService.findAllByPatientId(1L);

        //then
        assertThat(allByPatientId.size()).isEqualTo(3);
        for (VisitTO visitTO : allByPatientId) {
            assertThat(visitTO.getPatient_id()).isEqualTo(patientId);
        }

    }

}