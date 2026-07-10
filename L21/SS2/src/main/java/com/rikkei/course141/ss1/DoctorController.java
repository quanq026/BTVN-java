package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<ApiResponse<List<Doctor>>> getAll() {
        List<Doctor> list = doctorService.findAll();
        ApiResponse.Meta meta = new ApiResponse.Meta(list.size(), 1, list.size());
        return ResponseEntity.ok(ApiResponse.success(list, meta));
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(doctorService.findById(id)));
    }

    @PostMapping("/doctors")
    public ResponseEntity<ApiResponse<Doctor>> create(@Valid @RequestBody DoctorCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(doctorService.create(dto)));
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<ApiResponse<Doctor>> update(@PathVariable Long id, @RequestBody DoctorUpdateDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(doctorService.update(id, dto)));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departments/{departmentId}/doctors")
    public ResponseEntity<ApiResponse<List<Doctor>>> getByDepartment(@PathVariable Long departmentId) {
        List<Doctor> list = doctorService.findByDepartment(departmentId);
        return ResponseEntity.ok(ApiResponse.success(list));
    }
}
