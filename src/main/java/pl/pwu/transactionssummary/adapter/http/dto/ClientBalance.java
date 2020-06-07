package pl.pwu.transactionssummary.adapter.http.dto;

import java.math.BigDecimal;

public class ClientBalance {
    private BigDecimal total;
    private String currency;
    private String date;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
