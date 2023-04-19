package com.stgcodes.client.spotify.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Slf4j
public class CustomDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder()
                        .appendValue(ChronoField.YEAR, 4)
                        .optionalStart()
                        .appendPattern("-MM-[dd]")
                        .optionalEnd()
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .toFormatter();

        String date = parser.getText();
        return LocalDate.parse(date, formatter);
    }
}
