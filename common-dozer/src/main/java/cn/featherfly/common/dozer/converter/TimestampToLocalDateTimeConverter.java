package cn.featherfly.common.dozer.converter;

import com.github.dozermapper.core.DozerConverter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by zj
 */
public class TimestampToLocalDateTimeConverter extends DozerConverter<Timestamp, LocalDateTime> {

    public TimestampToLocalDateTimeConverter() {
        super(Timestamp.class, LocalDateTime.class);
    }


    @Override
    public LocalDateTime convertTo(Timestamp source, LocalDateTime destination) {
        if (source != null) {
            Instant instant = source.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        }
        return null;
    }

    @Override
    public Timestamp convertFrom(LocalDateTime source, Timestamp destination) {
        if (source != null) {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = source.atZone(zone).toInstant();
            return Timestamp.from(instant);
        }
        return null;
    }
}
