package cn.featherfly.common.dozer.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * DateToLocalDateConverter.
 *
 * @author zhongj
 */
public class DateToLocalDateConverter extends DozerConverter<Date, LocalDate> {

    public DateToLocalDateConverter() {
        super(Date.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(Date source, LocalDate destination) {
        if (source != null) {
            Instant instant = source.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone).toLocalDate();
        }
        return null;
    }

    @Override
    public Date convertFrom(LocalDate source, Date destination) {
        if (source != null) {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = source.atStartOfDay().atZone(zone).toInstant();
            return Date.from(instant);
        }
        return null;
    }
}
