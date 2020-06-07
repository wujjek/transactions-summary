package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RevenueAmountExtractorUnitTest {

    private final RevenueAmountExtractor sut =  new RevenueAmountExtractor();

    @Test
    public void shouldReturnZeroWhenOutcome(){
        int actual = sut.applyAsInt(TestTransactions.outcomeWithAmount(100));
        assertEquals(actual, 0);
    }

    @Test
    public void shouldIncludeIncome(){
        int expected = 100;
        int actual = sut.applyAsInt(TestTransactions.incomeWithAmount(expected));
        assertEquals(actual, expected);
    }

}