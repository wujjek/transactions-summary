package pl.pwu.transactionssummary.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.INCOME;
import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.OUTCOME;

public class TestTransactions {
    public static Transaction outcomeWithAmount(int amount){
        return new Transaction(OUTCOME, "test", LocalDate.now(), amount, Currency.getInstance(Locale.getDefault()));
    }

    public static Transaction incomeWithAmount(int amount){
        return new Transaction(INCOME, "test", LocalDate.now(), amount, Currency.getInstance(Locale.getDefault()));
    }

    public static Transaction future(Clock clock) {
        return new Transaction(INCOME, "test", LocalDate.now(clock).plusDays(1), 100, Currency.getInstance(Locale.getDefault()));
    }

    public static Transaction past(Clock clock) {
        return new Transaction(INCOME, "test", LocalDate.now(clock).minusDays(1), 100, Currency.getInstance(Locale.getDefault()));
    }

    public static Transaction current(Clock clock) {
        return new Transaction(INCOME, "test", LocalDate.now(clock), 100, Currency.getInstance(Locale.getDefault()));
    }
}
