package pl.pwu.transactionssummary.domain;

import java.time.Clock;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class CountingSummaryStrategy implements SummaryStrategy{

    public int count;

    @Override
    public Predicate<Transaction> transactionFilter(Clock clock) {
        return transaction -> {
            count++;
            return true;
        };
    }

    @Override
    public ToIntFunction<Transaction> amountExtractor() {
        return Transaction::getValue;
    }
}
