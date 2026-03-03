package com.codingexercise.service;

import com.codingexercise.model.request.LoanApplicationRequest;
import com.codingexercise.model.response.LoanApplicationResponse;

public interface LoanApplicationService {

    LoanApplicationResponse evaluate(LoanApplicationRequest request);
}