package com.inbank.decision;

import com.inbank.decision.exceptions.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class DecisionRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//added try catch to prevent nullpointer exception -  since variable creditModifier is of primitive type,
// a NullPointerException will occur during auto-boxing.
    public int getClientCreditModifier(String identityCode) {
        try {
        String sql = "SELECT credit_modifier FROM credit_score where identity_code =:dbIdentityCode";
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("dbIdentityCode", identityCode);

            var query = jdbcTemplate.queryForObject(sql, loanMap, Integer.class);
            if (query != null) {
                return query;
            }
        } catch (DataAccessException e) {
            log.warn("Query failed for identityCode: {}", identityCode);
        }
        throw new GeneralException("Check input parameters, incorrect query");
    }
}
