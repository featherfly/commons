package cn.featherfly.common.dozer.converter;

import com.github.dozermapper.core.DozerConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zj
 */
public class DateToStringFormatConverter extends DozerConverter<Date, String> {
    public DateToStringFormatConverter() {
        super(Date.class, String.class);
    }
    @Override
    public String convertTo(Date source, String destination) {
        String value = null;
        if (source != null) {
            DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            value = sf.format(source);
        }
        return value;
    }

    @Override
    public Date convertFrom(String source, Date destination) {
        Date value = null;
        try {
            if (source != null) {
                DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                value = sf.parse(source);
            }
            return value;
        }catch (ParseException e){
            e.printStackTrace();
        }
            return null;
    }
}
