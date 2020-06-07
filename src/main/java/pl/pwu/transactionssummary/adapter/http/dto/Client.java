package pl.pwu.transactionssummary.adapter.http.dto;

import java.util.List;
import java.util.Objects;

public class Client {

    private ClientInfo info;
    private ClientBalance balance;
    private List<Transaction> transactions;

    public ClientInfo getInfo() {
        return info;
    }

    public void setInfo(ClientInfo info) {
        this.info = info;
    }

    public ClientBalance getBalance() {
        return balance;
    }

    public void setBalance(ClientBalance balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(info, client.info) &&
                Objects.equals(balance, client.balance) &&
                Objects.equals(transactions, client.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info, balance, transactions);
    }
}
