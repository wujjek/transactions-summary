package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncomeTransactionFilterUnitTest {

    private final IncomeTransactionFilter sut = new IncomeTransactionFilter();

    @Test
    public void shouldFilterOutOutcome(){
        boolean actual = sut.test(TestTransactions.outcomeWithAmount(100));
        assertFalse(actual);
    }

    @Test
    public void shouldIncludeIncome(){
        boolean actual = sut.test(TestTransactions.incomeWithAmount(100));
        assertTrue(actual);
    }
}