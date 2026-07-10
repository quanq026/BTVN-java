package com.rikkei.course141.ss1;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PatientCreateDTO {
    @NotBlank
    private String fullName;
    @Min(0)
    @Max(150)
    private Integer age;
    @Pattern(regexp = "^(0)(3|5|7|8|9)[0-9]{8}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
}
