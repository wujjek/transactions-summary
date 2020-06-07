package pl.pwu.transactionssummary.domain;

import java.util.function.Predicate;

import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.INCOME;

public class IncomeTransactionFilter implements Predicate<Transaction> {

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getType()==INCOME;
    }
}