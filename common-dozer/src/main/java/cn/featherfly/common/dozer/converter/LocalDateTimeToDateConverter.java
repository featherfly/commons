package cn.featherfly.common.dozer.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * LocalDateTimeToDateConverter.
 *
 * @author zhongj
 */
public class LocalDateTimeToDateConverter extends DozerConverter<LocalDateTime, Date> {

    public LocalDateTimeToDateConverter() {
        super(LocalDateTime.class, Date.class);
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
    }

}