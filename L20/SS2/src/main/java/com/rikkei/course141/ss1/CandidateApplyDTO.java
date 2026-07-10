package com.rikkei.course141.ss1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CandidateApplyDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private MultipartFile cvFile;
}
