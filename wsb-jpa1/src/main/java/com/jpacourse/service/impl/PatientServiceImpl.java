package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        PatientEntity patientEntity = patientDao.findOne(id);
        return PatientMapper.mapToTO(patientEntity);
    }

    @Override
    public PatientTO save(PatientTO patientTO) {
        PatientEntity patientEntity = PatientMapper.mapToEntity(patientTO);
        PatientEntity savedEntity = patientDao.save(patientEntity);
        return PatientMapper.mapToTO(savedEntity);
    }

    @Override
    public PatientTO update(PatientTO patientTO) {
        PatientEntity existingPatient = patientDao.findOne(patientTO.getId());
        if (existingPatient == null) {
            return null;
        }
        PatientEntity updatedEntity = patientDao.update(PatientMapper.mapToEntity(patientTO));
        return PatientMapper.mapToTO(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        PatientEntity existingPatient = patientDao.findOne(id);
        if (existingPatient == null) {
            return false;
        }
        patientDao.delete(id);
        return true;
    }

}
