package com.inbank.decision;

import com.inbank.decision.exceptions.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionService {
    @Autowired
    private final DecisionRepository decisionRepository;

    private int getCreditModifier(String identityCode) {
        return decisionRepository.getClientCreditModifier(identityCode);
    }

    public double getCreditScore(int creditModifier, double inputAmount, int inputPeriod) {
        return (creditModifier / inputAmount) * inputPeriod;
    }

    public ResponseEntity<LoanDecisionResponse> getLoanDecision(double inputAmount, int inputPeriod, String identityCode) throws GeneralException {
        try {
            var creditModifier = getCreditModifier(identityCode);
            if (creditModifier >= 100) {
                var approvedInputPeriod = LoanUtils.maxPossibleLoanPeriod(inputPeriod);
                return new ResponseEntity<>(new LoanDecisionResponse()
                        .setApprovedLoanAmount(LoanUtils.maxPossibleLoanAmount(creditModifier, approvedInputPeriod))
                        .setApprovedLoanPeriod(approvedInputPeriod), HttpStatus.OK);
            }
        } catch (IntegrationException e) {
            log.warn("getLoanDecision failed for identityCode: {} with exception: {}", identityCode, e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
