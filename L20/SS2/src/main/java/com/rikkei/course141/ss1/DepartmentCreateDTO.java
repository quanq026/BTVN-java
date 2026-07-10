package com.rikkei.course141.ss1;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentCreateDTO {
    @NotBlank(message = "Tên phòng ban không được để trống")
    private String name;
    private String description;
}
