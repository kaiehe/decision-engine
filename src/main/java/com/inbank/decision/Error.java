package com.inbank.decision;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {
    private String description;
    private int errorCode;
    private String message;
}
