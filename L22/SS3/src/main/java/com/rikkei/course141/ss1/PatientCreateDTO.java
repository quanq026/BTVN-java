package com.rikkei.course141.ss1;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PatientCreateDTO {
    @NotBlank
    private String name;
    @Min(0)
    @Max(150)
    private Integer age;
    @NotBlank
    private String symptom;
}
