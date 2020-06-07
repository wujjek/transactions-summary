package pl.pwu.transactionssummary.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.pwu.transactionssummary.domain.TestTransactions.incomeWithAmount;
import static pl.pwu.transactionssummary.domain.TestTransactions.outcomeWithAmount;

public class TransactionsSummaryCalculatorUnitTest {

    public static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    public static final CurrencyExchange TEST_CURRENCY_EXCHANGE = (from, to, amount) -> amount;
    private TransactionsSummaryCalculator sut;
    private CountingSummaryStrategy summaryStrategy;

    @BeforeEach
    public void setUp(){
        summaryStrategy = new CountingSummaryStrategy();
        sut = new TransactionsSummaryCalculator(TEST_CURRENCY_EXCHANGE, summaryStrategy, CLOCK);
    }

    @Test
    public void shouldTryFilterOneTransactionAndReturnSameAmount(){
        int expectedAmount = 1;
        Currency expectedCurrency = Currency.getInstance(Locale.getDefault());

        TransactionsSummary summary = sut.calculate(List.of(incomeWithAmount(expectedAmount)), expectedCurrency);

        assertEquals(1, summaryStrategy.count);
        assertEquals(expectedAmount, summary.getAmount());
        assertEquals(expectedCurrency, summary.getCurrency());
    }

    @Test
    public void shouldReturnZeroForEmptyList(){
        Currency expectedCurrency = Currency.getInstance(Locale.getDefault());

        TransactionsSummary summary = sut.calculate(List.of(), expectedCurrency);

        assertEquals(0, summary.getAmount());
        assertEquals(expectedCurrency, summary.getCurrency());
    }

    @Test
    public void shouldTryFilterTransactionAndReturnSum(){
        int transactionCount = 4;
        int singleAmount = 100;
        int expectedAmount = transactionCount*singleAmount;
        Currency expectedCurrency = Currency.getInstance(Locale.getDefault());

        TransactionsSummary summary = sut.calculate(List.of(incomeWithAmount(singleAmount),
                outcomeWithAmount(singleAmount),
                incomeWithAmount(singleAmount),
                outcomeWithAmount(singleAmount)), expectedCurrency);

        assertEquals(transactionCount, summaryStrategy.count);
        assertEquals(expectedAmount, summary.getAmount());
        assertEquals(expectedCurrency, summary.getCurrency());
    }

    @Test
    public void shouldThrowIAEWhenTransactionsNull(){
        Currency expectedCurrency = Currency.getInstance(Locale.getDefault());
        CountingSummaryStrategy summaryStrategy = new CountingSummaryStrategy();
        TransactionsSummaryCalculator sut = new TransactionsSummaryCalculator(TEST_CURRENCY_EXCHANGE, summaryStrategy, CLOCK);

        assertThrows(IllegalArgumentException.class, ()->sut.calculate(null, expectedCurrency));
    }

    @Test
    public void shouldThrowIAEWhenCurrencyNull(){
        CountingSummaryStrategy summaryStrategy = new CountingSummaryStrategy();
        TransactionsSummaryCalculator sut = new TransactionsSummaryCalculator(TEST_CURRENCY_EXCHANGE, summaryStrategy, CLOCK);

        assertThrows(IllegalArgumentException.class, ()->sut.calculate(List.of(), null));
    }

}
