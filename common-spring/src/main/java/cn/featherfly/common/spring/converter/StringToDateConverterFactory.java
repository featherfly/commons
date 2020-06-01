package cn.featherfly.common.spring.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import cn.featherfly.common.lang.Lang;

public final class StringToDateConverterFactory implements ConverterFactory<String, Date> {

    private static final Logger LOG = LoggerFactory.getLogger(StringToDateConverterFactory.class);

    private String[] patterns = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
            "yyyy-MM-dd" };

    private List<DateFormat> dateFormats = new ArrayList<>();

    public StringToDateConverterFactory() {
        setPatterns(patterns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Date> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToDate<>(dateFormats);
    }

    private class StringToDate<T extends Date> implements Converter<String, T> {

        private List<DateFormat> dateFormats;

        public StringToDate(List<DateFormat> dateFormats) {
            this.dateFormats = dateFormats;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T convert(String source) {
            if (Lang.isEmpty(source)) {
                // It's an empty enum identifier: reset the enum value to null.
                return null;
            }
            for (DateFormat format : dateFormats) {
                try {
                    return (T) format.parse(source);
                } catch (Exception e) {
                    LOG.debug(e.getMessage());
                    continue;
                }
            }
            return null;
        }
    }

    /**
     * 返回patterns
     *
     * @return patterns
     */
    public String[] getPatterns() {
        return patterns;
    }

    /**
     * 设置patterns
     *
     * @param patterns patterns
     */
    public void setPatterns(String[] patterns) {
        this.patterns = patterns;
        dateFormats.clear();
        for (String pattern : patterns) {
            dateFormats.add(new SimpleDateFormat(pattern));
        }
    }
}