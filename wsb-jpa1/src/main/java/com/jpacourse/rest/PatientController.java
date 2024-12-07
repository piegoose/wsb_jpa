package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // GET /patients/{id} - Fetch a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientTO> getPatientById(@PathVariable Long id) {
        PatientTO patient = patientService.findById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    // POST /patients - Create a new patient
    @PostMapping
    public ResponseEntity<PatientTO> createPatient(@RequestBody PatientTO patientTO) {
        PatientTO savedPatient = patientService.save(patientTO);
        return ResponseEntity.ok(savedPatient);
    }

    // PUT /patients/{id} - Update an existing patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientTO> updatePatient(@PathVariable Long id, @RequestBody PatientTO patientTO) {
        patientTO.setId(id); // Ensure the ID is set
        PatientTO updatedPatient = patientService.update(patientTO);
        if (updatedPatient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPatient);
    }

    // DELETE /patients/{id} - Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
