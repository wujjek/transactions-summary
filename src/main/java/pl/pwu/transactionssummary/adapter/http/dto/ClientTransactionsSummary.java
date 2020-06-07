package pl.pwu.transactionssummary.adapter.http.dto;

import java.math.BigDecimal;
import java.util.Currency;

public class ClientTransactionsSummary {

    private final BigDecimal amount;
    private final String currency;

    public ClientTransactionsSummary(int amount, Currency currency) {
        this.amount = BigDecimal.valueOf(amount).movePointLeft(2);
        this.currency = currency.getCurrencyCode();
    }

    public ClientTransactionsSummary(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
