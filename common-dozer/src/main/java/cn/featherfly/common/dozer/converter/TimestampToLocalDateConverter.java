package cn.featherfly.common.dozer.converter;

import com.github.dozermapper.core.DozerConverter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by zj
 */
public class TimestampToLocalDateConverter extends DozerConverter<Timestamp, LocalDate> {

    public TimestampToLocalDateConverter() {
        super(Timestamp.class, LocalDate.class);
    }


    @Override
    public LocalDate convertTo(Timestamp source, LocalDate destination) {
        if (source != null) {
            Instant instant = source.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone).toLocalDate();
        }
        return null;
    }

    @Override
    public Timestamp convertFrom(LocalDate source, Timestamp destination) {
        if (source != null) {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = source.atStartOfDay().atZone(zone).toInstant();
            return Timestamp.from(instant);
        }
        return null;
    }
}
