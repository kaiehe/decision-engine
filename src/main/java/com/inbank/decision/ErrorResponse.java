package com.inbank.decision;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ErrorResponse {
    @Singular
    private List<Error> errors;
}
