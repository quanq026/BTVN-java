package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody PatientCreateDTO dto) {
        MDC.put("requestId", UUID.randomUUID().toString());
        try {
            log.info("Nhận request thêm bệnh nhân");
            Patient p = patientService.addPatient(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        } finally {
            MDC.clear();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        MDC.put("requestId", UUID.randomUUID().toString());
        try {
            return ResponseEntity.ok(patientService.findById(id));
        } finally {
            MDC.clear();
        }
    }
}
