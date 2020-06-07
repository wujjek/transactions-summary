package pl.pwu.transactionssummary.adapter.http;

import org.springframework.util.Assert;
import pl.pwu.transactionssummary.adapter.http.dto.Transaction;
import pl.pwu.transactionssummary.adapter.http.dto.TransactionType;

import java.util.Currency;
import java.util.Map;

import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.INCOME;
import static pl.pwu.transactionssummary.domain.Transaction.TransactionType.OUTCOME;

public class TransactionConverter {

    private static final Map<TransactionType, pl.pwu.transactionssummary.domain.Transaction.TransactionType> typeConversion = Map.of(
            TransactionType.INCOME, INCOME,
            TransactionType.OUTCOME, OUTCOME);

    public static pl.pwu.transactionssummary.domain.Transaction convert(Transaction transaction) {
        int amountInCents = transaction.getValue().movePointRight(2).intValue();
        return new pl.pwu.transactionssummary.domain.Transaction(convertType(transaction.getType()), transaction.getDescription(),
                transaction.getDate(), amountInCents, Currency.getInstance(transaction.getCurrency()));
    }

    private static pl.pwu.transactionssummary.domain.Transaction.TransactionType convertType(TransactionType type) {
        pl.pwu.transactionssummary.domain.Transaction.TransactionType toReturn = typeConversion.get(type);
        Assert.notNull(toReturn, "Unknown type:" + type);
        return toReturn;
    }
}
