package pl.pwu.transactionssummary.adapter.http;

import pl.pwu.transactionssummary.adapter.http.dto.Clients;

public class TransactionsSummaryRequest {

    private Clients clients;

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }
}
