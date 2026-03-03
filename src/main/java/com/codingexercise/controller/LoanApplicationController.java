package com.codingexercise.controller;

import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;
import com.codingexercise.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanApplicationController implements LoanApplicationApi {

    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<LoanApplicationResponse> createApplication(
            @Valid LoanApplicationRequest request) {

        LoanApplicationResponse response = service.evaluate(request);
        return ResponseEntity.ok(response);
    }
}