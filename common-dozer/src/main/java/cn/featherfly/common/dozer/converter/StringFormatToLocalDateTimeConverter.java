package cn.featherfly.common.dozer.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.dozermapper.core.DozerConverter;

/**
 * StringFormatToLocalDateTimeConverter.
 *
 * @author zhongj
 */
public class StringFormatToLocalDateTimeConverter extends DozerConverter<String, LocalDateTime> {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public StringFormatToLocalDateTimeConverter() {
        super(String.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertTo(String source, LocalDateTime destination) {
        if (source != null) {
            return LocalDateTime.parse(source, DATE_TIME_PATTERN);
        }
        return null;
    }

    @Override
    public String convertFrom(LocalDateTime source, String destination) {
        if (source != null) {
            return source.format(DATE_TIME_PATTERN);
        }
        return null;
    }
}
