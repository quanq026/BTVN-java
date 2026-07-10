package com.rikkei.course141.ss1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(PatientCreateDTO dto) {
        log.info("Tiếp nhận bệnh nhân: {}", dto.getName());
        if (dto.getAge() != null && dto.getAge() > 120) {
            log.warn("Tuổi bệnh nhân {} quá cao: {}", dto.getName(), dto.getAge());
        }
        Patient p = Patient.builder()
            .name(dto.getName())
            .age(dto.getAge())
            .symptom(dto.getSymptom())
            .build();
        Patient saved = patientRepository.save(p);
        log.info("Lưu bệnh nhân thành công, id={}", saved.getId());
        return saved;
    }

    public Patient findById(Long id) {
        log.info("Tìm bệnh nhân id={}", id);
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân id=" + id));
    }
}
