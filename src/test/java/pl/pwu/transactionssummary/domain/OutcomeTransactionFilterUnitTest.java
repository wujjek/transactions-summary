package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutcomeTransactionFilterUnitTest {

    private final OutcomeTransactionFilter sut = new OutcomeTransactionFilter();

    @Test
    public void shouldFilterOutIncome(){
        boolean actual = sut.test(TestTransactions.incomeWithAmount(100));
        assertFalse(actual);
    }

    @Test
    public void shouldIncludeOutcome(){
        boolean actual = sut.test(TestTransactions.outcomeWithAmount(100));
        assertTrue(actual);
    }
}