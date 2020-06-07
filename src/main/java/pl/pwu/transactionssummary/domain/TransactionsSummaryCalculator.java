package pl.pwu.transactionssummary.domain;

import org.springframework.util.Assert;

import java.time.Clock;
import java.util.Currency;
import java.util.List;

public class TransactionsSummaryCalculator {
    private final CurrencyExchange currencyExchange;
    private final SummaryStrategy summaryStrategy;
    private final Clock clock;

    public TransactionsSummaryCalculator(CurrencyExchange currencyExchange, SummaryStrategy summaryStrategy, Clock clock) {
        this.currencyExchange = currencyExchange;
        this.summaryStrategy = summaryStrategy;
        this.clock = clock;
    }

    public TransactionsSummary calculate(List<Transaction> transactions,  Currency targetCurrency) {
        Assert.notNull(transactions, "transactions required");
        Assert.notNull(targetCurrency, "targetCurrency required");
        int sum = transactions.stream()
                .map(transaction -> transaction.converse(targetCurrency, currencyExchange.converse(transaction.getCurrency(), targetCurrency, transaction.getValue())))
                .filter(summaryStrategy.transactionFilter(clock))
                .mapToInt(summaryStrategy.amountExtractor())
                .sum();
        return new TransactionsSummary(targetCurrency, sum);
    }
}
