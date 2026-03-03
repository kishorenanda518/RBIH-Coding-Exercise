package com.codingexercise.model.request;

import com.codingexercise.enums.EmploymentType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplicantDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 21, message = "Age must be at least 21")
    @Max(value = 60, message = "Age must not exceed 60")
    private int age;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.01", message = "Monthly income must be greater than 0")
    private BigDecimal monthlyIncome;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    @Min(value = 300, message = "Credit score must be at least 300")
    @Max(value = 900, message = "Credit score must not exceed 900")
    private int creditScore;
}