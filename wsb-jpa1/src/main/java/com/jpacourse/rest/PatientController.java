package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientTO> getPatientById(@PathVariable Long id) {
        PatientTO patient = patientService.findById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientTO> createPatient(@RequestBody PatientTO patientTO) {
        PatientTO savedPatient = patientService.save(patientTO);
        return ResponseEntity.ok(savedPatient);
    }
    @PostMapping("/{patientId}/visits")
    public ResponseEntity<String> addVisitToPatient(
            @PathVariable Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String visitDate,
            @RequestParam String description,
            @RequestBody List<String> treatmentTypes) {

        try {
            patientService.addVisitToPatient(
                    patientId,
                    doctorId,
                    LocalDateTime.parse(visitDate),
                    description,
                    treatmentTypes
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Wizyta została dodana pomyślnie!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd: " + e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient with ID " + id + " has been deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " has not been deleted.");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
