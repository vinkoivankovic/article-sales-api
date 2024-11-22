package com.example.article_sales_api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SalesContractStatus {
    CREATED("KREIRANO"),
    ORDERED("NARUČENO"),
    DELIVERED("ISPORUČENO"),
    DELETED("IZBRISANO");

    private final String croatianName;

    SalesContractStatus(String croatianName) {
        this.croatianName = croatianName;
    }

    public String getCroatianName() {
        return croatianName;
    }

    @JsonValue
    public String getCroatianNameForJson() {
        return croatianName;
    }

    @JsonCreator
    public static SalesContractStatus fromString(String croatianName) {
        for (SalesContractStatus status : values()) {
            if (status.getCroatianName().equalsIgnoreCase(croatianName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + croatianName);
    }
}