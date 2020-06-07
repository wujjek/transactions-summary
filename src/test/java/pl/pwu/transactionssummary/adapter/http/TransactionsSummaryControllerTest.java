package pl.pwu.transactionssummary.adapter.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pwu.transactionssummary.adapter.TestCurrencyExchange;
import pl.pwu.transactionssummary.adapter.http.dto.*;
import pl.pwu.transactionssummary.domain.CountingSummaryStrategy;
import pl.pwu.transactionssummary.domain.TransactionsSummaryCalculator;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionsSummaryControllerTest {

    public static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    private TransactionsSummaryController sut;
    private CountingSummaryStrategy summaryStrategy;

    @BeforeEach
    public void setUp(){
        summaryStrategy = new CountingSummaryStrategy();
        sut = new TransactionsSummaryController(Map.of(SummaryType.EXPENDITURE, new TransactionsSummaryCalculator(new TestCurrencyExchange(), summaryStrategy, CLOCK)));
    }

    @Test
    public void shouldRespondWithProperSummary(){
        List<ClientTransactionsSummaries> transactionsSummary = sut.getTransactionsSummary(request());

        assertEquals(4, summaryStrategy.count);
        assertEquals(1, transactionsSummary.size());
        assertEquals(getClientInfo(), transactionsSummary.get(0).getClientInfo());
        assertNotNull(transactionsSummary.get(0).getExpenditure());
        assertEquals(getClientBalance().getCurrency(), transactionsSummary.get(0).getExpenditure().getCurrency());
    }

    private TransactionsSummaryRequest request() {
        TransactionsSummaryRequest toReturn = new TransactionsSummaryRequest();
        Clients clients = new Clients();
        Client client = new Client();
        client.setBalance(getClientBalance());
        client.setInfo(getClientInfo());
        client.setTransactions(getTransactions());
        clients.setClient(List.of(client));
        toReturn.setClients(clients);
        return toReturn;
    }

    private List<Transaction> getTransactions() {
        Transaction t1 = new Transaction();
        t1.setCurrency("PLN");
        t1.setDate(LocalDate.of(2020,5, 4));
        t1.setDescription("salary");
        t1.setType(TransactionType.INCOME);
        t1.setValue(BigDecimal.valueOf(7500));

        Transaction t2 = new Transaction();
        t2.setCurrency("PLN");
        t2.setDate(LocalDate.of(2020,5, 6));
        t2.setDescription("mortgage");
        t2.setType(TransactionType.OUTCOME);
        t2.setValue(BigDecimal.valueOf(1100));

        Transaction t3 = new Transaction();
        t3.setCurrency("PLN");
        t3.setDate(LocalDate.of(2020,5, 10));
        t3.setDescription("intrests");
        t3.setType(TransactionType.INCOME);
        t3.setValue(BigDecimal.valueOf(1700));

        Transaction t4 = new Transaction();
        t4.setCurrency("PLN");
        t4.setDate(LocalDate.of(2020,5, 11));
        t4.setDescription("transfer");
        t4.setType(TransactionType.OUTCOME);
        t4.setValue(BigDecimal.valueOf(1200));

        return List.of(t4, t2, t3, t4);
    }

    private ClientInfo getClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setName("Tomasz");
        clientInfo.setSurname("Karczynski");
        return null;
    }

    private ClientBalance getClientBalance() {
        ClientBalance balance = new ClientBalance();
        balance.setCurrency("PLN");
        balance.setDate("01.05.2020");
        balance.setTotal(BigDecimal.valueOf(12110));
        return balance;
    }
}