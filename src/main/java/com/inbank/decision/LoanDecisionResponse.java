package com.inbank.decision;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoanDecisionResponse {
    private double approvedLoanAmount;
    private int approvedLoanPeriod;
}
