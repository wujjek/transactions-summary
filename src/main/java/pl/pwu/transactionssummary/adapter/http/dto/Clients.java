package pl.pwu.transactionssummary.adapter.http.dto;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    List<Client> client = new ArrayList<>();

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }
}

