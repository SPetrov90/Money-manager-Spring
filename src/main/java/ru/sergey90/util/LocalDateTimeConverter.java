package ru.sergey90.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * autoConvert LocalDateTime to Timestamp
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp ts) {
        return ts.toLocalDateTime();
    }
}
