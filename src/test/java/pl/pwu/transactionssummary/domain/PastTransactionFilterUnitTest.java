package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static pl.pwu.transactionssummary.domain.TestTransactions.*;

class PastTransactionFilterUnitTest {

    public static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    private final PastTransactionFilter sut = new PastTransactionFilter(CLOCK);

    @Test
    public void shouldFilterOutFutureTransaction(){
        Transaction futureTransaction = future(CLOCK);
        boolean actual = sut.test(futureTransaction);

        assertFalse(actual);
    }

    @Test
    public void shouldIncludePastTransaction(){
        Transaction pastTransaction = past(CLOCK);
        boolean actual = sut.test(pastTransaction);

        assertTrue(actual);
    }

    @Test
    public void shouldIncludeCurrentTransaction(){
        Transaction pastTransaction = current(CLOCK);
        boolean actual = sut.test(pastTransaction);

        assertTrue(actual);
    }
}