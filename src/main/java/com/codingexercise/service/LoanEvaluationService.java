package com.codingexercise.service;

import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;

public interface LoanEvaluationService {

    LoanApplicationResponse evaluate(LoanApplicationRequest request);
}