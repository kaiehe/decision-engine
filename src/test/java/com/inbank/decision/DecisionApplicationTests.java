package com.inbank.decision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class DecisionApplicationTests {
    @Autowired
    DecisionRepository decisionRepository;

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
