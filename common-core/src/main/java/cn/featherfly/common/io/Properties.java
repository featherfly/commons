
package cn.featherfly.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * Properties
 * </p>
 *
 * @author zhongj
 */
public interface Properties {

    String setProperty(String key, String value);

    String setProperty(String key, String value, String comment);

    Property setProperty(Property property);

    String getProperty(String key);

    String getProperty(String key, String defaultValue);

    Property getPropertyPart(String key);

    Collection<Property> getPropertyParts();

    Collection<String> getPropertyNames();

    Collection<Part> listAll();

    Charset getCharset();

    //    Properties comment(String... comment);

    void store(OutputStream out) throws IOException;

    void load(InputStream is) throws IOException;

    java.util.Properties toJdkProperties();

    public interface Part {
        String toPart();
    }

    public class Comment implements Part {

        String comment;

        /**
         *
         */
        public Comment() {
        }

        /**
         * @param comment
         */
        public Comment(String comment) {
            super();
            this.comment = comment;
        }

        /**
         * 返回comment
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * 设置comment
         *
         * @param comment comment
         */
        public void setComment(String comment) {
            this.comment = comment;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toPart() {
            return Chars.SHARP + comment.replaceAll(Chars.NEW_LINE, Chars.SPACE);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return comment;
        }
    }

    public class CharsetComment extends Comment {

        private Charset charset;

        public static CharsetComment createIfCan(String comment) {
            String charset;
            if (LangUtils.isNotEmpty(
                    charset = org.apache.commons.lang3.StringUtils.substringAfter(comment, "charset=").trim())) {
                return new CharsetComment(Charset.forName(charset));
            }
            return null;
        }

        /**
         * @param charset
         */
        public CharsetComment(Charset charset) {
            super("charset=" + charset.displayName());
            this.charset = charset;
        }

        /**
         * 返回charset
         *
         * @return charset
         */
        public Charset getCharset() {
            return charset;
        }

        /**
         * 设置charset
         *
         * @param charset charset
         */
        public void setCharset(Charset charset) {
            this.charset = charset;
        }
    }

    public class Property implements Part {

        private String key;

        private String value;

        private String comment;

        /**
         *
         */
        public Property() {
        }

        /**
         * @param key
         * @param value
         */
        public Property(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }

        /**
         * @param key
         * @param value
         * @param comment
         */
        public Property(String key, String value, String comment) {
            super();
            this.key = key;
            this.value = value;
            this.comment = comment;
        }

        /**
         * 返回key
         *
         * @return key
         */
        public String getKey() {
            return key;
        }

        /**
         * 设置key
         *
         * @param key key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * 返回value
         *
         * @return value
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value
         *
         * @param value value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 返回comment
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * 设置comment
         *
         * @param comment comment
         */
        public void setComment(String comment) {
            this.comment = comment;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toPart() {
            String result = "";
            if (comment != null) {
                result += new Comment(comment).toPart() + cn.featherfly.common.constant.Chars.NEW_LINE;
            }
            result += key + "=" + value;
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Property [key=" + key + ", value=" + value + ", comment=" + comment + "]";
        }
    }
}
