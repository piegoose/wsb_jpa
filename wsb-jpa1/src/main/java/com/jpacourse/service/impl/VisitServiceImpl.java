package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {
    private VisitDao visitDao;
    @Autowired
    public void VisitServiceImpl(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public VisitTO findById(Long id) {
        VisitEntity visitEntity = visitDao.findOne(id);
        return VisitMapper.mapToTO(visitEntity);
    }

    @Override
    public List<VisitTO> findAll() {
        return visitDao.findAll()
                .stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitTO> findAllByPatientId(Long patientId) {
        return visitDao.findByPatientId(patientId)
                .stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public VisitTO addVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String description, List<String> treatmentTypes) {
//
//        PatientEntity patientEntity = entityManager.find
//        if (patientEntity == null) {
//            throw new IllegalArgumentException("Patient id not found: " + patientId);
//
//        }
//    }
}
