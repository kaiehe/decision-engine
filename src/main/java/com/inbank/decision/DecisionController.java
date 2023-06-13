package com.inbank.decision;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@CustomRestControllerAdvice
@RequestMapping("api")
@RestController
@RequiredArgsConstructor
public class DecisionController {
    private final DecisionService decisionService;

    @GetMapping("/apply/{identityCode}/{amount}/{period}")
    ResponseEntity<LoanDecisionResponse> getLoanDecision(@PathVariable("identityCode") String identityCode,
                                                         @PathVariable("amount") double inputAmount, @PathVariable("period") int inputPeriod) {
        return decisionService.getLoanDecision(inputAmount, inputPeriod, identityCode);
    }


}
