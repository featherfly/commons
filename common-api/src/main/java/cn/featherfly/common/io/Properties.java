
package cn.featherfly.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;

import cn.featherfly.common.constant.Chars;

/**
 * Properties.
 *
 * @author zhongj
 */
public interface Properties {

    /**
     * Sets the property.
     *
     * @param key   the key
     * @param value the value
     * @return the string
     */
    String setProperty(String key, String value);

    /**
     * Sets the property.
     *
     * @param key     the key
     * @param value   the value
     * @param comment the comment
     * @return the string
     */
    String setProperty(String key, String value, String comment);

    /**
     * Sets the property.
     *
     * @param property the property
     * @return the property
     */
    Property setProperty(Property property);

    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    String getProperty(String key);

    /**
     * Gets the property.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the property
     */
    String getProperty(String key, String defaultValue);

    /**
     * Gets the property part.
     *
     * @param key the key
     * @return the property part
     */
    Property getPropertyPart(String key);

    /**
     * Gets the property parts.
     *
     * @return the property parts
     */
    Collection<Property> getPropertyParts();

    /**
     * Gets the property names.
     *
     * @return the property names
     */
    Collection<String> getPropertyNames();

    /**
     * List all.
     *
     * @return the collection
     */
    Collection<Part> listAll();

    /**
     * Gets the charset.
     *
     * @return the charset
     */
    Charset getCharset();

    //    Properties comment(String... comment);

    /**
     * Store.
     *
     * @param out the out
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void store(OutputStream out) throws IOException;

    /**
     * Store.
     *
     * @param out     the out
     * @param charset the charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void store(OutputStream out, Charset charset) throws IOException;

    /**
     * Load.
     *
     * @param is the is
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void load(InputStream is) throws IOException;

    /**
     * To jdk properties.
     *
     * @return the java.util. properties
     */
    java.util.Properties toJdkProperties();

    /**
     * The Interface Part.
     */
    public interface Part {

        /**
         * To part.
         *
         * @return the string
         */
        String toPart();
    }

    /**
     * The Class Comment.
     */
    public class Comment implements Part {

        /** The comment. */
        String comment;

        /**
         * Instantiates a new comment.
         */
        public Comment() {
        }

        /**
         * Instantiates a new comment.
         *
         * @param comment the comment
         */
        public Comment(String comment) {
            super();
            this.comment = comment;
        }

        /**
         * 返回comment.
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * 设置comment.
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

    /**
     * The Class CharsetComment.
     */
    public class CharsetComment extends Comment {

        private Charset charset;

        /**
         * Creates the if can.
         *
         * @param comment the comment
         * @return the charset comment
         */
        public static CharsetComment createIfCan(String comment) {
            String[] tokens = comment.split("=");
            if (tokens.length == 2 && "charset".equalsIgnoreCase(tokens[0].trim())) {
                return new CharsetComment(Charset.forName(tokens[1].trim()));
            }
            return null;
        }

        /**
         * Instantiates a new charset comment.
         *
         * @param charset the charset
         */
        public CharsetComment(Charset charset) {
            super("charset=" + charset.displayName());
            this.charset = charset;
        }

        /**
         * 返回charset.
         *
         * @return charset
         */
        public Charset getCharset() {
            return charset;
        }

        /**
         * 设置charset.
         *
         * @param charset charset
         */
        public void setCharset(Charset charset) {
            this.charset = charset;
        }
    }

    /**
     * The Class Property.
     */
    public class Property implements Part {

        private String key;

        private String value;

        private String comment;

        /**
         * Instantiates a new property.
         */
        public Property() {
        }

        /**
         * Instantiates a new property.
         *
         * @param key   the key
         * @param value the value
         */
        public Property(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }

        /**
         * Instantiates a new property.
         *
         * @param key     the key
         * @param value   the value
         * @param comment the comment
         */
        public Property(String key, String value, String comment) {
            super();
            this.key = key;
            this.value = value;
            this.comment = comment;
        }

        /**
         * 返回key.
         *
         * @return key
         */
        public String getKey() {
            return key;
        }

        /**
         * 设置key.
         *
         * @param key key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value.
         *
         * @param value value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 返回comment.
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * 设置comment.
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
