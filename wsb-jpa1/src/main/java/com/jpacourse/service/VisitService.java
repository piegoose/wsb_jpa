package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface VisitService {
    VisitTO findById(Long id);
    List<VisitTO> findAll();
}

