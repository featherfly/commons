package cn.featherfly.common.dozer.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * Created by zj
 */
public class DateToLocalDateTimeConverter extends DozerConverter<Date, LocalDateTime> {

    public DateToLocalDateTimeConverter() {
        super(Date.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertTo(Date source, LocalDateTime destination) {
        if (source != null) {
            Instant instant = source.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        }
        return null;
    }

    @Override
    public Date convertFrom(LocalDateTime source, Date destination) {
        if (source != null) {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = source.atZone(zone).toInstant();
            return Date.from(instant);
        }
        return null;
    }
}
