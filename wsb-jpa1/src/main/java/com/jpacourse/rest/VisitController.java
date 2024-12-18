package com.jpacourse.rest;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.service.VisitService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {
    private final VisitService visitService;
    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<VisitTO> getVisit(@PathVariable Long id) {
        VisitTO visit = visitService.findById(id);
        if (visit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(visit);

    }

    @GetMapping
    public ResponseEntity<List<VisitTO>> getAllVisits() {
        List<VisitTO> visits = visitService.findAll();
        return ResponseEntity.ok(visits);
    }


}
