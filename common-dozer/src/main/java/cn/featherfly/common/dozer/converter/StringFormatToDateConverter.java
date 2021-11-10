package cn.featherfly.common.dozer.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

/**
 * Created by zj
 */
public class StringFormatToDateConverter extends DozerConverter<String, Date> {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public StringFormatToDateConverter() {
        super(String.class, Date.class);
    }

    @Override
    public Date convertTo(String source, Date destination) {
        if (source != null && !"".equals(source)) {
            try {
                return FORMAT.parse(source);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }

    @Override
    public String convertFrom(Date source, String destination) {
        if (source != null) {
            return FORMAT.format(source);
        }
        return null;
    }
}
