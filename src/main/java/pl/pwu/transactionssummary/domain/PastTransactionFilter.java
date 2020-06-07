package pl.pwu.transactionssummary.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.util.function.Predicate;

public class PastTransactionFilter implements Predicate<Transaction> {

    private final Clock clock;

    public PastTransactionFilter(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean test(Transaction transaction) {
        LocalDate now = LocalDate.now(clock);
        return transaction.getDate().isBefore(now) || transaction.getDate().isEqual(now);
    }
}
