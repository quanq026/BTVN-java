package com.rikkei.course141.ss1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    private final Path uploadDir = Paths.get("uploads");

    @Transactional
    public Candidate apply(CandidateApplyDTO dto) throws IOException {
        MultipartFile file = dto.getCvFile();
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("Vui lòng đính kèm CV");
        }
        String original = file.getOriginalFilename();
        if (original == null || !original.toLowerCase().endsWith(".pdf")) {
            throw new InvalidFileException("CV phải là file PDF");
        }
        Files.createDirectories(uploadDir);
        String fileName = UUID.randomUUID() + "_" + original;
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        String cvUrl = "/uploads/" + fileName;
        Candidate c = Candidate.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .cvUrl(cvUrl)
            .build();
        return candidateRepository.save(c);
    }
}
