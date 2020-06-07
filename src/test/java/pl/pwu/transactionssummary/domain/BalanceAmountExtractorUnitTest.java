package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pwu.transactionssummary.domain.TestTransactions.incomeWithAmount;
import static pl.pwu.transactionssummary.domain.TestTransactions.outcomeWithAmount;

class BalanceAmountExtractorUnitTest {

    private final BalanceAmountExtractor sut = new BalanceAmountExtractor();

    @Test
    public void shouldReturnMinusWhenOutcome(){
        int expected = -100;
        Transaction transaction = outcomeWithAmount(-expected);
        int actual = sut.applyAsInt(transaction);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSameWhenIncome(){
        int expected = 100;
        Transaction transaction = incomeWithAmount(expected);
        int actual = sut.applyAsInt(transaction);

        assertEquals(expected, actual);
    }
}