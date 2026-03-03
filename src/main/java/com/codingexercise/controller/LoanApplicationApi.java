package com.codingexercise.controller;

import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Loan Application API", description = "APIs for evaluating loan applications")
@RequestMapping("/applications")
public interface LoanApplicationApi {

    @Operation(
            summary = "Evaluate Loan Application",
            description = "Receives a loan application, evaluates eligibility rules, " +
                    "and generates a single loan offer based on requested tenure."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<LoanApplicationResponse> createApplication(
            @RequestBody LoanApplicationRequest request
    );
}