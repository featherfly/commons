
package cn.featherfly.common.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.DateUtils;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * PropertiesImpl
 * </p>
 *
 * @author zhongj
 */
public class PropertiesImpl implements Properties {

    private Charset charset = StandardCharsets.ISO_8859_1;

    private Map<String, Part> partMap = new LinkedHashMap<>();

    private Properties defaults;

    /**
     * Creates an empty property list with no default values.
     */
    public PropertiesImpl() {
        super();
    }

    /**
     * Creates an empty property list with the specified defaults.
     *
     * @param defaults the defaults.
     */
    public PropertiesImpl(Properties defaults) {
        this(defaults.getCharset());
        this.defaults = defaults;
        int i = 1;
        for (Part part : defaults.listAll()) {
            if (part instanceof Property) {
                partMap.put(((Property) part).getKey(), part);
            } else {
                partMap.put("comments[" + i + "]", part);
                i++;
            }
        }
    }

    /**
     * Creates an empty property list with no default values.
     *
     * @param charset charset
     */
    public PropertiesImpl(Charset charset) {
        super();
        if (charset != null) {
            this.charset = charset;
        }
    }

    /**
     * Creates an empty property list with no default values.
     *
     * @param properties properties
     */
    public PropertiesImpl(java.util.Properties properties) {
        this(properties, null);
    }

    /**
     * Creates an empty property list with no default values.
     *
     * @param properties java.util.Properties
     * @param charset    charset
     */
    public PropertiesImpl(java.util.Properties properties, Charset charset) {
        this(charset);
        if (properties != null) {
            properties.stringPropertyNames().forEach(pn -> {
                setProperty(pn, properties.getProperty(pn));
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setProperty(String key, String value) {
        return setProperty(key, value, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setProperty(String key, String value, String comment) {
        Property property = setProperty(new Property(key, value, comment));
        return property == null ? null : property.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setProperty(Property property) {
        return (Property) partMap.put(property.getKey(), property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(String key) {
        Property p = getPropertyPart(key);
        if (LangUtils.isNotEmpty(p)) {
            return p.getValue();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return val == null ? defaultValue : val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property getPropertyPart(String key) {
        Property p = (Property) partMap.get(key);
        if (p == null && defaults != null) {
            p = defaults.getPropertyPart(key);
        }
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Property> getPropertyParts() {
        return partMap.values().stream().filter(p -> p instanceof Property).map(p -> (Property) p)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getPropertyNames() {
        return partMap.keySet();
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public Properties comment(String... comment) {
    //        for (String c : comment) {
    //            partMap.put(UUIDGenerator.generateUUID22Letters(), new Comment(c));
    //        }
    //        return this;
    //    }

    private String addComment(String comment) {
        String key = System.currentTimeMillis() + "";
        partMap.put(key, new Comment(comment));
        return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Part> listAll() {
        return partMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Charset getCharset() {
        return charset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Properties toJdkProperties() {
        java.util.Properties properties = new java.util.Properties();
        getPropertyParts().forEach(p -> properties.put(p.getKey(), p.getValue()));
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(OutputStream out) throws IOException {
        store0(new BufferedWriter(new OutputStreamWriter(out, charset)), charset == StandardCharsets.ISO_8859_1,
                charset);
        //        if (charset == StandardCharsets.ISO_8859_1) {
        //            //            store0(new BufferedWriter(new OutputStreamWriter(out, "8859_1")), comments, true);
        //            store0(new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.ISO_8859_1)), true,
        //                    StandardCharsets.ISO_8859_1);
        //        } else {
        //            store0(new BufferedWriter(new OutputStreamWriter(out, charset)), false, charset);
        //        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(InputStream is) throws IOException {
        load0(new LineReader(is));
    }

    private void load0(LineReader lr) throws IOException {
        char[] convtBuf = new char[1024];
        int limit;
        int keyLen;
        int valueStart;
        char c;
        boolean hasSep;
        boolean precedingBackslash;

        String comment = null;
        String commentKey = null;
        boolean firstLine = true;
        while ((limit = lr.readLine()) >= 0) {
            c = 0;
            keyLen = 0;
            valueStart = limit;
            hasSep = false;
            precedingBackslash = false;
            // 处理注释
            if (Chars.SHARP_CHAR == lr.lineBuf[0] || Chars.NOT_L_CHAR == lr.lineBuf[0]) {
                comment = loadConvert(lr.lineBuf, 0, limit, convtBuf).substring(1);
                if (LangUtils.isNotEmpty(comment)) {
                    comment = StringUtils.encode(comment, StandardCharsets.ISO_8859_1.displayName(),
                            charset.displayName());
                    CharsetComment cc;
                    if (firstLine && (cc = CharsetComment.createIfCan(comment)) != null) {
                        charset = cc.getCharset();
                        firstLine = false;
                        continue;
                    }
                    commentKey = addComment(comment);
                    firstLine = false;
                    continue;
                }
            }
            firstLine = false;

            while (keyLen < limit) {
                c = lr.lineBuf[keyLen];
                //need check if escaped.
                if ((c == '=' || c == ':') && !precedingBackslash) {
                    valueStart = keyLen + 1;
                    hasSep = true;
                    break;
                } else if ((c == ' ' || c == '\t' || c == '\f') && !precedingBackslash) {
                    valueStart = keyLen + 1;
                    break;
                }
                if (c == '\\') {
                    precedingBackslash = !precedingBackslash;
                } else {
                    precedingBackslash = false;
                }
                keyLen++;
            }
            while (valueStart < limit) {
                c = lr.lineBuf[valueStart];
                if (c != ' ' && c != '\t' && c != '\f') {
                    if (!hasSep && (c == '=' || c == ':')) {
                        hasSep = true;
                    } else {
                        break;
                    }
                }
                valueStart++;
            }
            String key = loadConvert(lr.lineBuf, 0, keyLen, convtBuf);
            String value = loadConvert(lr.lineBuf, valueStart, limit - valueStart, convtBuf);
            setProperty(StringUtils.encode(key, StandardCharsets.ISO_8859_1.displayName(), charset.displayName()),
                    StringUtils.encode(value, StandardCharsets.ISO_8859_1.displayName(), charset.displayName()),
                    comment);
            partMap.remove(commentKey);
            comment = null;
        }
    }

    private String loadConvert(char[] in, int off, int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    private void store0(BufferedWriter bw, boolean escUnicode, Charset charset) throws IOException {
        bw.write(Chars.SHARP + "charset=" + charset.displayName() + Chars.NEW_LINE);
        bw.write(Chars.SHARP + "updated at " + DateUtils.formartTime(new Date()) + Chars.NEW_LINE);
        //        bw.newLine();
        synchronized (this) {
            for (Part part : listAll()) {
                if (part instanceof Property) {
                    Property property = (Property) part;
                    String key = saveConvert(property.getKey(), true, escUnicode);
                    String val = saveConvert(property.getValue(), false, escUnicode);
                    //                    String comment = saveConvert(property.getComment(), false, escUnicode);
                    Property np = new Property(key, val, LangUtils.isNotEmpty(property.getComment(), comm -> {
                        return saveConvert(comm, false, escUnicode);
                    }));
                    bw.write(np.toPart());
                    bw.write(Chars.NEW_LINE);
                    //                    bw.newLine();
                } else {
                    Comment nc = new Comment();
                    if (charset == null || charset == StandardCharsets.ISO_8859_1) {
                        nc.setComment(StringUtils.stringToUnicode(((Comment) part).getComment()));
                    } else {
                        nc.setComment(((Comment) part).getComment());
                    }
                    bw.write(nc.toPart());
                    bw.write(Chars.NEW_LINE);
                    //                    bw.newLine();
                }
            }
        }
        bw.flush();
    }

    private String saveConvert(String theString, boolean escapeSpace, boolean escapeUnicode) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if (aChar > 61 && aChar < 127) {
                if (aChar == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
                case ' ':
                    if (x == 0 || escapeSpace) {
                        outBuffer.append('\\');
                    }
                    outBuffer.append(' ');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\');
                    outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020 || aChar > 0x007e) & escapeUnicode) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex(aChar >> 12 & 0xF));
                        outBuffer.append(toHex(aChar >> 8 & 0xF));
                        outBuffer.append(toHex(aChar >> 4 & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    class LineReader {
        public LineReader(InputStream inStream) {
            this.inStream = inStream;
            inByteBuf = new byte[8192];
        }

        public LineReader(Reader reader) {
            this.reader = reader;
            inCharBuf = new char[8192];
        }

        byte[] inByteBuf;
        char[] inCharBuf;
        char[] lineBuf = new char[1024];
        int inLimit = 0;
        int inOff = 0;
        InputStream inStream;
        Reader reader;

        int readLine() throws IOException {
            int len = 0;
            char c = 0;

            boolean skipWhiteSpace = true;
            boolean isCommentLine = false;
            boolean isNewLine = true;
            boolean appendedLineBegin = false;
            boolean precedingBackslash = false;
            boolean skipLF = false;

            while (true) {
                if (inOff >= inLimit) {
                    inLimit = inStream == null ? reader.read(inCharBuf) : inStream.read(inByteBuf);
                    inOff = 0;
                    if (inLimit <= 0) {
                        if (len == 0 || isCommentLine) {
                            return -1;
                        }
                        if (precedingBackslash) {
                            len--;
                        }
                        return len;
                    }
                }
                if (inStream != null) {
                    //The line below is equivalent to calling a
                    //ISO8859-1 decoder.
                    c = (char) (0xff & inByteBuf[inOff++]);
                } else {
                    c = inCharBuf[inOff++];
                }
                if (skipLF) {
                    skipLF = false;
                    if (c == '\n') {
                        continue;
                    }
                }
                if (skipWhiteSpace) {
                    if (c == ' ' || c == '\t' || c == '\f') {
                        continue;
                    }
                    if (!appendedLineBegin && (c == '\r' || c == '\n')) {
                        continue;
                    }
                    skipWhiteSpace = false;
                    appendedLineBegin = false;
                }
                if (isNewLine) {
                    isNewLine = false;
                    if (c == '#' || c == '!') {
                        // 取消掉忽略注释
                        //                        isCommentLine = true;
                        //                        continue;
                    }
                }

                if (c != '\n' && c != '\r') {
                    lineBuf[len++] = c;
                    if (len == lineBuf.length) {
                        int newLength = lineBuf.length * 2;
                        if (newLength < 0) {
                            newLength = Integer.MAX_VALUE;
                        }
                        char[] buf = new char[newLength];
                        System.arraycopy(lineBuf, 0, buf, 0, lineBuf.length);
                        lineBuf = buf;
                    }
                    //flip the preceding backslash flag
                    if (c == '\\') {
                        precedingBackslash = !precedingBackslash;
                    } else {
                        precedingBackslash = false;
                    }
                } else {
                    // reached EOL
                    if (isCommentLine || len == 0) {
                        isCommentLine = false;
                        isNewLine = true;
                        skipWhiteSpace = true;
                        len = 0;
                        continue;
                    }
                    if (inOff >= inLimit) {
                        inLimit = inStream == null ? reader.read(inCharBuf) : inStream.read(inByteBuf);
                        inOff = 0;
                        if (inLimit <= 0) {
                            if (precedingBackslash) {
                                len--;
                            }
                            return len;
                        }
                    }
                    if (precedingBackslash) {
                        len -= 1;
                        //skip the leading whitespace characters in following line
                        skipWhiteSpace = true;
                        appendedLineBegin = true;
                        precedingBackslash = false;
                        if (c == '\r') {
                            skipLF = true;
                        }
                    } else {
                        return len;
                    }
                }
            }
        }
    }

    /**
     * Convert a nibble to a hex character
     *
     * @param nibble the nibble to convert.
     */
    private static char toHex(int nibble) {
        return hexDigit[nibble & 0xF];
    }

    /** A table of hex digits */
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

}
