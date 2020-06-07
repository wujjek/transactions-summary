package pl.pwu.transactionssummary.domain;

import java.util.Currency;

public interface CurrencyExchange {
    int converse(Currency from, Currency to, int amount);
}
