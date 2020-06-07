package pl.pwu.transactionssummary.domain;

import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Currency;

public class Transaction {

    private final TransactionType type;
    private final String description;
    private final LocalDate date;
    private final int value;
    private final Currency currency;

    public Transaction(TransactionType type, String description, LocalDate date, int value, Currency currency) {
        Assert.notNull(type, "type not null");
        Assert.notNull(description, "description not null");
        Assert.notNull(date, "date not null");
        Assert.notNull(currency, "currency not null");
        this.type = type;
        this.description = description;
        this.date = date;
        this.value = value;
        this.currency = currency;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Transaction converse(Currency newCurrency, int newAmount){
        return new Transaction(type, description, date, newAmount, newCurrency);
    }

    public enum  TransactionType {
        INCOME,
        OUTCOME,
    }
}
