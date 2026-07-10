package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient create(PatientCreateDTO dto) {
        Patient p = Patient.builder()
            .fullName(dto.getFullName())
            .age(dto.getAge())
            .phoneNumber(dto.getPhoneNumber())
            .build();
        return patientRepository.save(p);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân với ID: " + id));
    }
}
