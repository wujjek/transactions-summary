package pl.pwu.transactionssummary.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.INCOME;
import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.OUTCOME;

public class BalanceAmountExtractor implements ToIntFunction<Transaction> {

    private static final Map<Transaction.TransactionType, Function<Transaction, Integer>> amountExtractionStrategy = Map.of(
            OUTCOME, transaction -> -transaction.getValue(),
            INCOME, Transaction::getValue);

    @Override
    public int applyAsInt(Transaction value) {
        return amountExtractionStrategy.get(value.getType()).apply(value);
    }
}
