package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private final Path uploadDir = Paths.get("uploads");

    public Employee create(EmployeeCreateDTO dto) {
        departmentRepository.findById(dto.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại"));
        Employee e = Employee.builder()
            .fullName(dto.getFullName())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .salary(dto.getSalary())
            .departmentId(dto.getDepartmentId())
            .build();
        return employeeRepository.save(e);
    }

    public Employee uploadAvatar(Long id, MultipartFile file) throws IOException {
        Employee e = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nhân viên không tồn tại"));
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("Vui lòng chọn file");
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new InvalidFileException("File phải nhỏ hơn 2MB");
        }
        String original = file.getOriginalFilename();
        if (original == null || !(original.toLowerCase().endsWith(".jpg") || original.toLowerCase().endsWith(".png") || original.toLowerCase().endsWith(".jpeg"))) {
            throw new InvalidFileException("Định dạng file không hợp lệ");
        }
        Files.createDirectories(uploadDir);
        String fileName = UUID.randomUUID() + "_" + original;
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        e.setAvatarUrl("/uploads/" + fileName);
        return employeeRepository.save(e);
    }
}
