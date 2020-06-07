package pl.pwu.transactionssummary.adapter.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pwu.transactionssummary.adapter.http.dto.Client;
import pl.pwu.transactionssummary.adapter.http.dto.ClientTransactionsSummaries;
import pl.pwu.transactionssummary.domain.Transaction;
import pl.pwu.transactionssummary.domain.TransactionsSummaryCalculator;

import java.util.Currency;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@RestController
public class TransactionsSummaryController {

    private final Map<SummaryType, TransactionsSummaryCalculator> summaryCalculators;

    public TransactionsSummaryController(Map<SummaryType, TransactionsSummaryCalculator> summaryCalculators) {
        this.summaryCalculators = summaryCalculators;
    }

    @PostMapping("/transactions/summary")
    public List<ClientTransactionsSummaries> getTransactionsSummary(@RequestBody TransactionsSummaryRequest transactionsSummaryRequest) {
        List<Client> clients = transactionsSummaryRequest.getClients().getClient();
        return clients.stream()
                .collect(toMap(client -> client, this::clientDomainTransactions))
                .entrySet().stream()
                .map(this::calculateSummary)
                .collect(toList());
    }

    private List<Transaction> clientDomainTransactions(Client client) {
        return client.getTransactions().stream().map(TransactionConverter::convert).collect(toList());
    }

    private ClientTransactionsSummaries calculateSummary(Map.Entry<Client, List<Transaction>> clientTransactions) {
        ClientTransactionsSummaries clientTransactionsSummary = new ClientTransactionsSummaries(clientTransactions.getKey());
        summaryCalculators.forEach((key, value) -> clientTransactionsSummary.add(key, value.calculate(clientTransactions.getValue(), Currency.getInstance(clientTransactions.getKey().getBalance().getCurrency()))));
        return clientTransactionsSummary;
    }
}
