package com.codingexercise.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanApplicationRequest {

    @Valid
    @NotNull(message = "Applicant details are required")
    private ApplicantDTO applicant;

    @Valid
    @NotNull(message = "Loan details are required")
    private LoanDetailsDTO loan;
}