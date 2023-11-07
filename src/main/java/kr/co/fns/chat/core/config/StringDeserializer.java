package kr.co.fns.chat.core.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class StringDeserializer extends com.fasterxml.jackson.databind.deser.std.StringDeserializer {

    @Override
    public String deserialize(JsonParser p, DeserializationContext dcx) throws IOException {
        String value = super.deserialize(p, dcx);
        return value != null ? value.trim() : null;
    }
}