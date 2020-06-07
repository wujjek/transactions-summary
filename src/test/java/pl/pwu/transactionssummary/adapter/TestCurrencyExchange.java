package pl.pwu.transactionssummary.adapter;

import pl.pwu.transactionssummary.domain.CurrencyExchange;

import java.util.Currency;

public class TestCurrencyExchange implements CurrencyExchange {
    @Override
    public int converse(Currency from, Currency to, int amount) {
        return amount;
    }
}
