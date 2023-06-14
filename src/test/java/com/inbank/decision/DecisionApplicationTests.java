package com.inbank.decision;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DecisionApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    DecisionRepository decisionRepository;

    @Test
    @DisplayName("Return data for client with positive credit rating")
    void shouldReturnData_whenQueryingAcceptedIdentityCode() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/apply/49002010998/2000/60", String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        var amount = documentContext.read("$.approvedLoanAmount");
        Assertions.assertEquals(10000.0, amount);

        var period = documentContext.read("$.approvedLoanPeriod");
        Assertions.assertEquals(60, period);
    }

    @Test
    @DisplayName("Return error for client with debt")
    void shouldReturnError_whenQueryingClientWithDebt() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/apply/49002010965/2000/12", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Check credit score calculation")
    void whenRequestCreditModifier_returnPositiveCreditScoreForSelectedPerson() {
        var creditModifier = decisionRepository.getClientCreditModifier("49002010976");
        var creditScore = DecisionService.getCreditScore(creditModifier, 3000, 12);
        Assertions.assertEquals(creditScore, 0.4);
        Assertions.assertInstanceOf(Double.class, creditScore);
    }

    @Test
    @DisplayName("Check no response when person with debt category")
    void whenRequestCreditModifierForDebt_returnNull() {
        var creditModifier = decisionRepository.getClientCreditModifier("49002010965");
        var creditScore = DecisionService.getCreditScore(creditModifier, 3000, 12);
        Assertions.assertEquals(creditScore, 0.0);
    }

}
