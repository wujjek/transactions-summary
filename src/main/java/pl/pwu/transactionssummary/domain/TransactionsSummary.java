package pl.pwu.transactionssummary.domain;

import java.util.Currency;

public class TransactionsSummary {

    private final Currency currency;
    private final int amount;

    public TransactionsSummary(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }
}
