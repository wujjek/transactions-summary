package pl.pwu.transactionssummary.adapter.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
    @JsonProperty("income")
    INCOME,
    @JsonProperty("outcome")
    OUTCOME,
    ;
}
