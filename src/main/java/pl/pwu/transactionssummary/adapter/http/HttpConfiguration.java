package pl.pwu.transactionssummary.adapter.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pwu.transactionssummary.domain.*;

import java.time.Clock;
import java.util.Currency;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static pl.pwu.transactionssummary.adapter.http.SummaryType.*;

@Configuration
public class HttpConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean
    public CurrencyExchange currencyExchange(){
        return (from, to, amount) -> amount;
    }

    @Bean
    public SummaryStrategy balanceSummaryStrategy(){
        return new SummaryStrategy() {
            @Override
            public Predicate<pl.pwu.transactionssummary.domain.Transaction> transactionFilter(Clock clock) {
                return new PastTransactionFilter(clock);
            }

            @Override
            public ToIntFunction<pl.pwu.transactionssummary.domain.Transaction> amountExtractor() {
                return new BalanceAmountExtractor();
            }
        };
    }

    @Bean
    public SummaryStrategy turnoverSummaryStrategy(){
        return new SummaryStrategy() {
            @Override
            public Predicate<pl.pwu.transactionssummary.domain.Transaction> transactionFilter(Clock clock) {
                return new PastTransactionFilter(clock);
            }

            @Override
            public ToIntFunction<pl.pwu.transactionssummary.domain.Transaction> amountExtractor() {
                return new TurnoverAmountExtractor();
            }
        };
    }

    @Bean
    public SummaryStrategy revenueSummaryStrategy(){
        return new SummaryStrategy() {
            @Override
            public Predicate<pl.pwu.transactionssummary.domain.Transaction> transactionFilter(Clock clock) {
                return new PastTransactionFilter(clock).and(new IncomeTransactionFilter());
            }

            @Override
            public ToIntFunction<pl.pwu.transactionssummary.domain.Transaction> amountExtractor() {
                return new RevenueAmountExtractor();
            }
        };
    }

    @Bean
    public SummaryStrategy expenditureSummaryStrategy(){
        return new SummaryStrategy() {
            @Override
            public Predicate<pl.pwu.transactionssummary.domain.Transaction> transactionFilter(Clock clock) {
                return new PastTransactionFilter(clock).and(new OutcomeTransactionFilter());
            }

            @Override
            public ToIntFunction<pl.pwu.transactionssummary.domain.Transaction> amountExtractor() {
                return new ExpenditureAmountExtractor();
            }
        };
    }

    @Bean
    public Map<SummaryType, TransactionsSummaryCalculator> summaryCalculators(CurrencyExchange currencyExchange,
                                                                              SummaryStrategy balanceSummaryStrategy,
                                                                              SummaryStrategy revenueSummaryStrategy,
                                                                              SummaryStrategy expenditureSummaryStrategy,
                                                                              SummaryStrategy turnoverSummaryStrategy,
                                                                              Clock clock){
        return Map.of(
                BALANCE, new TransactionsSummaryCalculator(currencyExchange, balanceSummaryStrategy, clock),
                REVENUE, new TransactionsSummaryCalculator(currencyExchange, revenueSummaryStrategy, clock),
                EXPENDITURE, new TransactionsSummaryCalculator(currencyExchange, expenditureSummaryStrategy, clock),
                TURNOVER, new TransactionsSummaryCalculator(currencyExchange, turnoverSummaryStrategy, clock));
    }

}
