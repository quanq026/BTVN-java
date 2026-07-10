package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Doctor create(DoctorCreateDTO dto) {
        departmentRepository.findById(dto.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Doctor d = Doctor.builder()
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .specializationName(dto.getSpecializationName())
            .email(dto.getEmail())
            .departmentId(dto.getDepartmentId())
            .build();
        return doctorRepository.save(d);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id));
    }

    public Doctor update(Long id, DoctorUpdateDTO dto) {
        Doctor d = findById(id);
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setSpecializationName(dto.getSpecializationName());
        return doctorRepository.save(d);
    }

    public void delete(Long id) {
        doctorRepository.delete(findById(id));
    }

    public List<Doctor> findByDepartment(Long departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }
}
