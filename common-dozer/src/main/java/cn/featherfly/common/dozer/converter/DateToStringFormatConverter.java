package cn.featherfly.common.dozer.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * Created by zj
 */
public class DateToStringFormatConverter extends DozerConverter<Date, String> {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateToStringFormatConverter() {
        super(Date.class, String.class);
    }

    @Override
    public String convertTo(Date source, String destination) {
        String value = null;
        if (source != null) {
            value = FORMAT.format(source);
        }
        return value;
    }

    @Override
    public Date convertFrom(String source, Date destination) {
        Date value = null;
        try {
            if (source != null) {
                value = FORMAT.parse(source);
            }
            return value;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
