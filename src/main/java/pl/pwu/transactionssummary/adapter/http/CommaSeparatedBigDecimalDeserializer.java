package pl.pwu.transactionssummary.adapter.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class CommaSeparatedBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueAsString = p.getValueAsString().replace(",", ".");
        return new BigDecimal(valueAsString);
    }
}
