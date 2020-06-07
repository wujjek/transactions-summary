package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenditureAmountExtractorUnitTest {

    private final ExpenditureAmountExtractor sut =  new ExpenditureAmountExtractor();

    @Test
    public void shouldReturnZeroWhenIncome(){
        int actual = sut.applyAsInt(TestTransactions.incomeWithAmount(100));
        assertEquals(actual, 0);
    }

    @Test
    public void shouldIncludeOutcome(){
        int expected = 100;
        int actual = sut.applyAsInt(TestTransactions.outcomeWithAmount(expected));
        assertEquals(actual, expected);
    }

}