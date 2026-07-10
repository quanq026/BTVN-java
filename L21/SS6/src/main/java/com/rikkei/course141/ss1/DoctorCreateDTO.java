package com.rikkei.course141.ss1;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DoctorCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String specializationName;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Long departmentId;
}
