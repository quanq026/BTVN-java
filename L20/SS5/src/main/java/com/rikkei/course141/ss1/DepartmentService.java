package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department create(DepartmentCreateDTO dto) {
        Department d = Department.builder().name(dto.getName()).description(dto.getDescription()).build();
        return departmentRepository.save(d);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại"));
    }
}
