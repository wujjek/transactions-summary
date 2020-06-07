package pl.pwu.transactionssummary.adapter.http.dto;

import pl.pwu.transactionssummary.adapter.http.SummaryType;
import pl.pwu.transactionssummary.domain.TransactionsSummary;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static pl.pwu.transactionssummary.adapter.http.SummaryType.*;

public class ClientTransactionsSummaries {

    private final ClientInfo clientInfo;
    private final BigDecimal openingBalance;
    public ClientTransactionsSummary balance;
    public ClientTransactionsSummary revenue;
    public ClientTransactionsSummary expenditure;
    public ClientTransactionsSummary turnover;

    private final Map<SummaryType, Consumer<ClientTransactionsSummary>> STRATEGIES = Map.of(
            BALANCE, new Consumer<>() {
                @Override
                public void accept(ClientTransactionsSummary clientTransactionsSummary) {
                    BigDecimal balanceAdjusted = openingBalance.add(clientTransactionsSummary.getAmount());
                    setBalance(new ClientTransactionsSummary(balanceAdjusted, clientTransactionsSummary.getCurrency()));
                }
            },
            REVENUE, this::setRevenue,
            EXPENDITURE, this::setExpenditure,
            TURNOVER, this::setTurnover);

    public ClientTransactionsSummaries(Client client) {
        this.clientInfo = client.getInfo();
        this.openingBalance = client.getBalance().getTotal();
    }

    public void add(SummaryType summaryType, TransactionsSummary transactionsSummary) {
        ClientTransactionsSummary clientTransactionsSummary = convert(transactionsSummary);
        STRATEGIES.get(summaryType).accept(clientTransactionsSummary);
    }

    private ClientTransactionsSummary convert(TransactionsSummary transactionsSummary) {
        return new ClientTransactionsSummary(transactionsSummary.getAmount(), transactionsSummary.getCurrency());
    }

    public void setBalance(ClientTransactionsSummary balance) {
        this.balance = balance;
    }

    public ClientTransactionsSummary getBalance() {
        return balance;
    }

    public ClientTransactionsSummary getRevenue() {
        return revenue;
    }

    public void setRevenue(ClientTransactionsSummary revenue) {
        this.revenue = revenue;
    }

    public ClientTransactionsSummary getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(ClientTransactionsSummary expenditure) {
        this.expenditure = expenditure;
    }

    public ClientTransactionsSummary getTurnover() {
        return turnover;
    }

    public void setTurnover(ClientTransactionsSummary turnover) {
        this.turnover = turnover;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientTransactionsSummaries that = (ClientTransactionsSummaries) o;
        return Objects.equals(clientInfo, that.clientInfo) &&
                Objects.equals(openingBalance, that.openingBalance) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(revenue, that.revenue) &&
                Objects.equals(expenditure, that.expenditure) &&
                Objects.equals(turnover, that.turnover) &&
                Objects.equals(STRATEGIES, that.STRATEGIES);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientInfo, openingBalance, balance, revenue, expenditure, turnover, STRATEGIES);
    }
}