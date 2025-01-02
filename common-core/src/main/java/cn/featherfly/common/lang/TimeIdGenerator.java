
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 00:14:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.featherfly.common.lang.number.Radix;

/**
 * time id generator.
 *
 * @author zhongj
 */
public class TimeIdGenerator {

    private static final int IP_ADDRESS;

    static {
        int ip;
        try {
            ip = Num.toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ip = 0;
        }
        IP_ADDRESS = ip;
    }

    //    private static final String PATTERN = "yyyyMMddHHmmssSSS";
    private static final String PATTERN = "yyyyMMddHHmmss";

    private final DateFormat format;

    private final int timeFieldLength;

    private final Radix radix;

    private short counter = 0;

    private String formatIp;

    private boolean appendIpToStart;

    /**
     * Instantiates a new time id generator.
     */
    public TimeIdGenerator() {
        this(Radix.RADIX10, false);
    }

    /**
     * Instantiates a new time id generator.
     *
     * @param radix the radix
     * @param appendIp the append ip
     */
    public TimeIdGenerator(Radix radix, boolean appendIp) {
        this(PATTERN, radix, appendIp ? false : null);
    }

    /**
     * Instantiates a new time id generator.
     *
     * @param pattern the pattern
     * @param radix the radix
     * @param appendIpToStart the append ip to start
     */
    private TimeIdGenerator(String pattern, Radix radix, Boolean appendIpToStart) {
        super();
        this.radix = radix;
        format = new SimpleDateFormat(pattern);
        if (appendIpToStart != null) {
            this.appendIpToStart = appendIpToStart;
            formatIp = format(IP_ADDRESS);
        }

        switch (radix) {
            case RADIX10:
                timeFieldLength = pattern.length();
                break;
            case RADIX16:
            case RADIX32:
                String[] ss = ArrayUtils.createFill(String.class, pattern.length(), "9");
                timeFieldLength = new BigInteger(ArrayUtils.toString(ss, (char) 0)).toString(radix.value()).length();
                break;
            default:
                throw new IllegalArgumentException("only support for radix 10,16,32");
        }
    }

    /**
     * Generate.
     *
     * @return the serializable
     */
    public Serializable generate() {
        String id = null;
        if (radix != Radix.RADIX10) {
            id = Num.toString(Long.parseLong(format.format(new Date())), radix);
            if (id.length() < timeFieldLength) {
                StringBuilder buf = new StringBuilder();
                for (int i = 0; i < timeFieldLength; i++) {
                    buf.append("0");
                }
                buf.replace(buf.length() - id.length(), buf.length(), id);
                id = buf.toString();
            }
        } else {
            id = format.format(new Date());
        }
        id = id + format(getCount());
        if (formatIp != null) {
            if (appendIpToStart) {
                id = formatIp + id;
            } else {
                id = id + formatIp;
            }
        }
        return id;
    }

    /**
     * Gets the time.
     *
     * @param generatedId the generated id
     * @return the time
     */
    public long getTime(String generatedId) {
        if (formatIp != null && appendIpToStart) {
            return Num.parse(generatedId.substring(formatIp.length(), timeFieldLength), radix, 0L);
        } else {
            return Num.parse(generatedId.substring(0, timeFieldLength), radix, 0L);
        }
    }

    /**
     * Gets the ip.
     *
     * @param generatedId the generated id
     * @return the ip
     */
    public int getIp(String generatedId) {
        if (formatIp != null) {
            if (appendIpToStart) {
                return Num.parse(generatedId.substring(0, formatIp.length()), radix, 0);
            } else {
                return Num.parse(generatedId.substring(generatedId.length() - formatIp.length(), generatedId.length()),
                    radix, 0);
            }
        }
        return 0;
    }

    public String getIpStr(String generatedId) {
        int ip = getIp(generatedId);
        byte[] bs = Num.toByteArray(ip);
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            sb.append(Byte.toUnsignedInt(b)).append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String format(int intValue) {
        StringBuilder buf = null;
        String formatted = Num.toString(intValue, radix);
        switch (radix) {
            case RADIX16:
                buf = new StringBuilder("00000000");
                buf.replace(8 - formatted.length(), 8, formatted);
                return buf.toString();
            case RADIX32:
                buf = new StringBuilder("0000000");
                buf.replace(7 - formatted.length(), 7, formatted);
                return buf.toString();
            default:
                buf = new StringBuilder("000000000");
                buf.replace(9 - formatted.length(), 9, formatted);
                return buf.toString();
        }
    }

    private String format(short shortValue) {
        String formatted = Num.toString(shortValue, radix);
        if (radix == Radix.RADIX32) {
            StringBuilder buf = new StringBuilder("000");
            buf.replace(3 - formatted.length(), 3, formatted);
            return buf.toString();
        } else {
            StringBuilder buf = new StringBuilder("0000");
            buf.replace(4 - formatted.length(), 4, formatted);
            return buf.toString();
        }
    }

    private short getCount() {
        synchronized (UUIDHexGenerator.class) {
            if (counter < 0 || counter > 9999) {
                counter = 0;
            }
            return counter++;
        }
    }

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Class Builder.
     *
     * @author zhongj
     */
    public static class Builder {

        private String parttern = PATTERN;

        private Radix radix = Radix.RADIX10;

        private Boolean appendIpToStart;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super();
        }

        /**
         * Parttern.
         *
         * @param parttern the parttern
         * @return the builder
         */
        public Builder parttern(String parttern) {
            this.parttern = parttern;
            return this;
        }

        /**
         * radix.
         *
         * @param radix the radix
         * @return the builder
         */
        public Builder radix(Radix radix) {
            this.radix = radix;
            return this;
        }

        /**
         * Radix 10.
         *
         * @return the builder
         */
        public Builder radix10() {
            radix = Radix.RADIX10;
            return this;
        }

        /**
         * Radix 16.
         *
         * @return the builder
         */
        public Builder radix16() {
            radix = Radix.RADIX16;
            return this;
        }

        /**
         * Radix 32.
         *
         * @return the builder
         */
        public Builder radix32() {
            radix = Radix.RADIX32;
            return this;
        }

        /**
         * Append ip to start.
         *
         * @return the builder
         */
        public Builder appendIpToStart() {
            appendIpToStart = true;
            return this;
        }

        /**
         * Append ip to end.
         *
         * @return the builder
         */
        public Builder appendIpToEnd() {
            appendIpToStart = false;
            return this;
        }

        /**
         * Builds the.
         *
         * @return the time id generator
         */
        public TimeIdGenerator build() {
            return new TimeIdGenerator(parttern, radix, appendIpToStart);
        }

    }

}
