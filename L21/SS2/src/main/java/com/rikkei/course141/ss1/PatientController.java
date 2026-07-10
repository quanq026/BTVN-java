package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> create(@Valid @RequestBody PatientCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(patientService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(patientService.findById(id)));
    }
}
