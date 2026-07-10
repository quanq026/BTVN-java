package com.rikkei.course141.ss1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<Candidate>> apply(@Valid @ModelAttribute CandidateApplyDTO dto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(candidateService.apply(dto)));
    }
}
