package pl.pwu.transactionssummary.domain;

import java.time.Clock;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public interface SummaryStrategy {

    Predicate<Transaction> transactionFilter(Clock clock);

    ToIntFunction<Transaction> amountExtractor();
}
