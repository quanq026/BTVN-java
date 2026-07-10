package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> create(@Valid @RequestBody DepartmentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(departmentService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(departmentService.findById(id)));
    }
}
