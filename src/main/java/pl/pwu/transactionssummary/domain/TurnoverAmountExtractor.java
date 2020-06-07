package pl.pwu.transactionssummary.domain;

import java.util.function.ToIntFunction;

public class TurnoverAmountExtractor implements ToIntFunction<Transaction>{

    @Override
    public int applyAsInt(Transaction value) {
        return Math.abs(value.getValue());
    }
}
