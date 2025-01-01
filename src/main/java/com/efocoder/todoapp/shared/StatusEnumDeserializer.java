package com.efocoder.todoapp.shared;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusEnumDeserializer extends JsonDeserializer<StatusEnum> {
    @Override
    public StatusEnum deserialize(JsonParser p, DeserializationContext ctxt) throws  IOException {
        int value = p.getIntValue();
        return StatusEnum.valueToEnum(value);
    }
}
