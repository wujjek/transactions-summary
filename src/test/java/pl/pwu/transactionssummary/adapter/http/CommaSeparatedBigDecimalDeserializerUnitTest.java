package pl.pwu.transactionssummary.adapter.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

class CommaSeparatedBigDecimalDeserializerUnitTest {

    private ObjectMapper mapper;
    private CommaSeparatedBigDecimalDeserializer sut;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        sut = new CommaSeparatedBigDecimalDeserializer();
    }

    @Test
    public void floating_point_string_deserialises_to_Double_value() throws IOException {
        BigDecimal expected = BigDecimal.valueOf(11, 1);
        String json = String.format("{\"value\":%s}", "\"1,1\"");
        BigDecimal actual = deserialiseBigDecimal(json);
        Assertions.assertEquals(expected, actual);
    }

    private BigDecimal deserialiseBigDecimal(String json) throws IOException {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return sut.deserialize(parser, ctxt);
    }
}