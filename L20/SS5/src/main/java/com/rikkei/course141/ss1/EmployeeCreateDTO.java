package com.rikkei.course141.ss1;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeCreateDTO {
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "^(0)(3|5|7|8|9)[0-9]{8}$", message = "Số điện thoại không hợp lệ")
    private String phone;
    @Min(0)
    private Double salary;
    @NotNull
    private Long departmentId;
}
