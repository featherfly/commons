package cn.featherfly.common.dozer.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * Created by zj
 */
public class LocalDateTimeToDateConverter extends DozerConverter<LocalDateTime, Date> {

    public LocalDateTimeToDateConverter() {
        super(LocalDateTime.class, Date.class);
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        return dateTime;
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        Date convertToDate = Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        return convertToDate;
    }

}