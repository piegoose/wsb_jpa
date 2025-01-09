package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.VisitDao;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitService {
    VisitTO findById(Long id);
    List<VisitTO> findAll();
    List<VisitTO> findAllByPatientId(Long patientId);
//    VisitTO addVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes);
}

