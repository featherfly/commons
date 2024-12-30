
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.Test;

/**
 * <p>
 * NumberUtilsberUtilsTest
 * </p>
 *
 * @author zhongj
 */
public class NumberUtilsTest {

    @Test
    public void testLongToString62() {
        String string64 = NumberUtils.toString62Unit(1);
        assertEquals(string64, "1");
        string64 = NumberUtils.toString62Unit(10);
        assertEquals(string64, "a");
        string64 = NumberUtils.toString62Unit(60);
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString62Unit(61);
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString62Unit(62);
        assertEquals(string64, "10");
        string64 = NumberUtils.toString62Unit(63);
        assertEquals(string64, "11");
        string64 = NumberUtils.toString62Unit(123);
        assertEquals(string64, "1Z");
        string64 = NumberUtils.toString62Unit(124);
        assertEquals(string64, "20");

        // ---

        string64 = NumberUtils.toString62Unit(-1);
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString62Unit(-10);
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString62Unit(-60);
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString62Unit(-61);
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString62Unit(-62);
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString62Unit(-63);
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString62Unit(-123);
        assertEquals(string64, "-1Z");
        string64 = NumberUtils.toString62Unit(-124);
        assertEquals(string64, "-20");
    }

    @Test
    public void testBigIntegerToString62() {
        String string64 = NumberUtils.toString62Unit(BigInteger.valueOf(1));
        assertEquals(string64, "1");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(10));
        assertEquals(string64, "a");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(60));
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(61));
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(62));
        assertEquals(string64, "10");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(63));
        assertEquals(string64, "11");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(123));
        assertEquals(string64, "1Z");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(124));
        assertEquals(string64, "20");

        // ---

        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-1));
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-10));
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-60));
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-61));
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-62));
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-63));
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-123));
        assertEquals(string64, "-1Z");
        string64 = NumberUtils.toString62Unit(BigInteger.valueOf(-124));
        assertEquals(string64, "-20");
    }

    @Test
    public void testLongToString64() {
        String string64 = NumberUtils.toString64Unit(1);
        assertEquals(string64, "1");
        string64 = NumberUtils.toString64Unit(10);
        assertEquals(string64, "a");
        string64 = NumberUtils.toString64Unit(60);
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString64Unit(61);
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString64Unit(62);
        assertEquals(string64, "_");
        string64 = NumberUtils.toString64Unit(63);
        assertEquals(string64, "~");
        string64 = NumberUtils.toString64Unit(64);
        assertEquals(string64, "10");
        string64 = NumberUtils.toString64Unit(65);
        assertEquals(string64, "11");
        string64 = NumberUtils.toString64Unit(126);
        assertEquals(string64, "1_");
        string64 = NumberUtils.toString64Unit(127);
        assertEquals(string64, "1~");
        string64 = NumberUtils.toString64Unit(128);
        assertEquals(string64, "20");

        // ---
        string64 = NumberUtils.toString64Unit(-1);
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString64Unit(-10);
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString64Unit(-60);
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString64Unit(-61);
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString64Unit(-62);
        assertEquals(string64, "-_");
        string64 = NumberUtils.toString64Unit(-63);
        assertEquals(string64, "-~");
        string64 = NumberUtils.toString64Unit(-64);
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString64Unit(-65);
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString64Unit(-126);
        assertEquals(string64, "-1_");
        string64 = NumberUtils.toString64Unit(-127);
        assertEquals(string64, "-1~");
        string64 = NumberUtils.toString64Unit(-128);
        assertEquals(string64, "-20");
    }

    @Test
    public void testBigIntegerToString64() {
        String string64 = NumberUtils.toString64Unit(BigInteger.valueOf(1));
        assertEquals(string64, "1");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(10));
        assertEquals(string64, "a");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(60));
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(61));
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(62));
        assertEquals(string64, "_");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(63));
        assertEquals(string64, "~");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(64));
        assertEquals(string64, "10");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(65));
        assertEquals(string64, "11");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(127));
        assertEquals(string64, "1~");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(128));
        assertEquals(string64, "20");

        // ---

        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-1));
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-10));
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-60));
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-61));
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-62));
        assertEquals(string64, "-_");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-63));
        assertEquals(string64, "-~");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-64));
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-65));
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-127));
        assertEquals(string64, "-1~");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-128));
    }

    @Test
    public void testLongToString93() {
        String string64 = NumberUtils.toString93Unit(1);
        assertEquals(string64, "1");
        string64 = NumberUtils.toString93Unit(10);
        assertEquals(string64, "a");
        string64 = NumberUtils.toString93Unit(60);
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString93Unit(61);
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString93Unit(62);
        assertEquals(string64, "_");
        string64 = NumberUtils.toString93Unit(63);
        assertEquals(string64, "~");
        string64 = NumberUtils.toString93Unit(64);
        assertEquals(string64, "!");
        string64 = NumberUtils.toString93Unit(65);
        assertEquals(string64, "\"");
        string64 = NumberUtils.toString93Unit(91);
        assertEquals(string64, "|");
        string64 = NumberUtils.toString93Unit(92);
        assertEquals(string64, "}");
        string64 = NumberUtils.toString93Unit(93);
        assertEquals(string64, "10");
        string64 = NumberUtils.toString93Unit(94);
        assertEquals(string64, "11");
        string64 = NumberUtils.toString93Unit(184);
        assertEquals(string64, "1|");
        string64 = NumberUtils.toString93Unit(185);
        assertEquals(string64, "1}");

        // ---
        string64 = NumberUtils.toString93Unit(-1);
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString93Unit(-10);
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString93Unit(-60);
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString93Unit(-61);
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString93Unit(-62);
        assertEquals(string64, "-_");
        string64 = NumberUtils.toString93Unit(-63);
        assertEquals(string64, "-~");
        string64 = NumberUtils.toString93Unit(-64);
        assertEquals(string64, "-!");
        string64 = NumberUtils.toString93Unit(-65);
        assertEquals(string64, "-\"");
        string64 = NumberUtils.toString93Unit(-91);
        assertEquals(string64, "-|");
        string64 = NumberUtils.toString93Unit(-92);
        assertEquals(string64, "-}");
        string64 = NumberUtils.toString93Unit(-93);
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString93Unit(-94);
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString93Unit(-184);
        assertEquals(string64, "-1|");
        string64 = NumberUtils.toString93Unit(-185);
        assertEquals(string64, "-1}");
    }

    @Test
    public void testBigIntegerToString93() {
        String string64 = NumberUtils.toString93Unit(BigInteger.valueOf(1));
        assertEquals(string64, "1");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(10));
        assertEquals(string64, "a");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(60));
        assertEquals(string64, "Y");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(61));
        assertEquals(string64, "Z");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(62));
        assertEquals(string64, "_");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(63));
        assertEquals(string64, "~");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(64));
        assertEquals(string64, "!");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(65));
        assertEquals(string64, "\"");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(91));
        assertEquals(string64, "|");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(92));
        assertEquals(string64, "}");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(93));
        assertEquals(string64, "10");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(94));
        assertEquals(string64, "11");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(184));
        assertEquals(string64, "1|");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(185));
        assertEquals(string64, "1}");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(186));
        assertEquals(string64, "20");

        // ---

        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-1));
        assertEquals(string64, "-1");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-10));
        assertEquals(string64, "-a");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-60));
        assertEquals(string64, "-Y");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-61));
        assertEquals(string64, "-Z");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-62));
        assertEquals(string64, "-_");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-63));
        assertEquals(string64, "-~");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-64));
        assertEquals(string64, "-!");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-65));
        assertEquals(string64, "-\"");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-91));
        assertEquals(string64, "-|");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-92));
        assertEquals(string64, "-}");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-93));
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-94));
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-184));
        assertEquals(string64, "-1|");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-185));
        assertEquals(string64, "-1}");
        string64 = NumberUtils.toString93Unit(BigInteger.valueOf(-186));
        assertEquals(string64, "-20");
    }

    @Test
    public void testLongToString128() {
        String string128 = NumberUtils.toString128Unit(1);
        assertEquals(string128, "1");
        string128 = NumberUtils.toString128Unit(10);
        assertEquals(string128, "a");
        string128 = NumberUtils.toString128Unit(60);
        assertEquals(string128, "Y");
        string128 = NumberUtils.toString128Unit(61);
        assertEquals(string128, "Z");
        string128 = NumberUtils.toString128Unit(62);
        assertEquals(string128, "_");
        string128 = NumberUtils.toString128Unit(63);
        assertEquals(string128, "~");
        string128 = NumberUtils.toString128Unit(64);
        assertEquals(string128, "!");
        string128 = NumberUtils.toString128Unit(65);
        assertEquals(string128, "\"");
        string128 = NumberUtils.toString128Unit(126);
        assertEquals(string128, "¯");
        string128 = NumberUtils.toString128Unit(127);
        assertEquals(string128, "®");
        string128 = NumberUtils.toString128Unit(128);
        assertEquals(string128, "10");
        string128 = NumberUtils.toString128Unit(129);
        assertEquals(string128, "11");
        string128 = NumberUtils.toString128Unit(256);
        assertEquals(string128, "20");

        // ---

        string128 = NumberUtils.toString128Unit(-1);
        assertEquals(string128, "-1");
        string128 = NumberUtils.toString128Unit(-10);
        assertEquals(string128, "-a");
        string128 = NumberUtils.toString128Unit(-60);
        assertEquals(string128, "-Y");
        string128 = NumberUtils.toString128Unit(-61);
        assertEquals(string128, "-Z");
        string128 = NumberUtils.toString128Unit(-62);
        assertEquals(string128, "-_");
        string128 = NumberUtils.toString128Unit(-63);
        assertEquals(string128, "-~");
        string128 = NumberUtils.toString128Unit(-64);
        assertEquals(string128, "-!");
        string128 = NumberUtils.toString128Unit(-65);
        assertEquals(string128, "-\"");
        string128 = NumberUtils.toString128Unit(-126);
        assertEquals(string128, "-¯");
        string128 = NumberUtils.toString128Unit(-127);
        assertEquals(string128, "-®");
        string128 = NumberUtils.toString128Unit(-128);
        assertEquals(string128, "-10");
        string128 = NumberUtils.toString128Unit(-129);
        assertEquals(string128, "-11");
        string128 = NumberUtils.toString128Unit(-256);
        assertEquals(string128, "-20");
    }

    @Test
    public void testBigIntegerToString128() {
        String string128 = NumberUtils.toString128Unit(BigInteger.valueOf(1));
        assertEquals(string128, "1");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(10));
        assertEquals(string128, "a");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(60));
        assertEquals(string128, "Y");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(61));
        assertEquals(string128, "Z");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(62));
        assertEquals(string128, "_");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(63));
        assertEquals(string128, "~");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(64));
        assertEquals(string128, "!");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(65));
        assertEquals(string128, "\"");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(126));
        assertEquals(string128, "¯");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(127));
        assertEquals(string128, "®");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(128));
        assertEquals(string128, "10");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(129));
        assertEquals(string128, "11");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(256));
        assertEquals(string128, "20");

        // ---

        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-1));
        assertEquals(string128, "-1");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-10));
        assertEquals(string128, "-a");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-60));
        assertEquals(string128, "-Y");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-61));
        assertEquals(string128, "-Z");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-62));
        assertEquals(string128, "-_");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-63));
        assertEquals(string128, "-~");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-64));
        assertEquals(string128, "-!");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-65));
        assertEquals(string128, "-\"");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-126));
        assertEquals(string128, "-¯");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-127));
        assertEquals(string128, "-®");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-128));
        assertEquals(string128, "-10");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-129));
        assertEquals(string128, "-11");
        string128 = NumberUtils.toString128Unit(BigInteger.valueOf(-256));
        assertEquals(string128, "-20");
    }

    @Test
    public void testParseString62() {
        BigInteger bi = NumberUtils.parse62Unit("1");
        assertEquals(bi.intValue(), 1);
        bi = NumberUtils.parse62Unit("a");
        assertEquals(bi.intValue(), 10);
        bi = NumberUtils.parse62Unit("Y");
        assertEquals(bi.intValue(), 60);
        bi = NumberUtils.parse62Unit("Z");
        assertEquals(bi.intValue(), 61);
        bi = NumberUtils.parse62Unit("10");
        assertEquals(bi.intValue(), 62);
        bi = NumberUtils.parse62Unit("1Z");
        assertEquals(bi.intValue(), 123);
        bi = NumberUtils.parse62Unit("20");
        assertEquals(bi.intValue(), 124);

        bi = NumberUtils.parse62Unit("-1");
        assertEquals(bi.intValue(), -1);
        bi = NumberUtils.parse62Unit("-a");
        assertEquals(bi.intValue(), -10);
        bi = NumberUtils.parse62Unit("-Y");
        assertEquals(bi.intValue(), -60);
        bi = NumberUtils.parse62Unit("-Z");
        assertEquals(bi.intValue(), -61);
        bi = NumberUtils.parse62Unit("-10");
        assertEquals(bi.intValue(), -62);
        bi = NumberUtils.parse62Unit("-1Z");
        assertEquals(bi.intValue(), -123);
        bi = NumberUtils.parse62Unit("-20");
        assertEquals(bi.intValue(), -124);

        long longValue = NumberUtils.parse62UnitToLong("1");
        assertEquals(longValue, 1);
        longValue = NumberUtils.parse62UnitToLong("a");
        assertEquals(longValue, 10);
        longValue = NumberUtils.parse62UnitToLong("Y");
        assertEquals(longValue, 60);
        longValue = NumberUtils.parse62UnitToLong("Z");
        assertEquals(longValue, 61);
        longValue = NumberUtils.parse62UnitToLong("10");
        assertEquals(longValue, 62);
        longValue = NumberUtils.parse62UnitToLong("1Z");
        assertEquals(longValue, 123);
        longValue = NumberUtils.parse62UnitToLong("20");
        assertEquals(longValue, 124);

        longValue = NumberUtils.parse62UnitToLong("-1");
        assertEquals(longValue, -1);
        longValue = NumberUtils.parse62UnitToLong("-a");
        assertEquals(longValue, -10);
        longValue = NumberUtils.parse62UnitToLong("-Y");
        assertEquals(longValue, -60);
        longValue = NumberUtils.parse62UnitToLong("-Z");
        assertEquals(longValue, -61);
        longValue = NumberUtils.parse62UnitToLong("-10");
        assertEquals(longValue, -62);
        longValue = NumberUtils.parse62UnitToLong("-1Z");
        assertEquals(longValue, -123);
        longValue = NumberUtils.parse62UnitToLong("-20");
        assertEquals(longValue, -124);
    }

    @Test
    public void testParseString64() {
        BigInteger bi = NumberUtils.parse64Unit("1");
        assertEquals(bi.intValue(), 1);
        bi = NumberUtils.parse64Unit("a");
        assertEquals(bi.intValue(), 10);
        bi = NumberUtils.parse64Unit("Y");
        assertEquals(bi.intValue(), 60);
        bi = NumberUtils.parse64Unit("Z");
        assertEquals(bi.intValue(), 61);
        bi = NumberUtils.parse64Unit("_");
        assertEquals(bi.intValue(), 62);
        bi = NumberUtils.parse64Unit("~");
        assertEquals(bi.intValue(), 63);
        bi = NumberUtils.parse64Unit("10");
        assertEquals(bi.intValue(), 64);
        bi = NumberUtils.parse64Unit("1~");
        assertEquals(bi.intValue(), 127);
        bi = NumberUtils.parse64Unit("20");
        assertEquals(bi.intValue(), 128);

        bi = NumberUtils.parse64Unit("-1");
        assertEquals(bi.intValue(), -1);
        bi = NumberUtils.parse64Unit("-a");
        assertEquals(bi.intValue(), -10);
        bi = NumberUtils.parse64Unit("-Y");
        assertEquals(bi.intValue(), -60);
        bi = NumberUtils.parse64Unit("-Z");
        assertEquals(bi.intValue(), -61);
        bi = NumberUtils.parse64Unit("-_");
        assertEquals(bi.intValue(), -62);
        bi = NumberUtils.parse64Unit("-~");
        assertEquals(bi.intValue(), -63);
        bi = NumberUtils.parse64Unit("-10");
        assertEquals(bi.intValue(), -64);
        bi = NumberUtils.parse64Unit("-1~");
        assertEquals(bi.intValue(), -127);
        bi = NumberUtils.parse64Unit("-20");
        assertEquals(bi.intValue(), -128);

        long longValue = NumberUtils.parse64UnitToLong("1");
        assertEquals(longValue, 1);
        longValue = NumberUtils.parse64UnitToLong("a");
        assertEquals(longValue, 10);
        longValue = NumberUtils.parse64UnitToLong("Y");
        assertEquals(longValue, 60);
        longValue = NumberUtils.parse64UnitToLong("Z");
        assertEquals(longValue, 61);
        longValue = NumberUtils.parse64UnitToLong("_");
        assertEquals(longValue, 62);
        longValue = NumberUtils.parse64UnitToLong("~");
        assertEquals(longValue, 63);
        longValue = NumberUtils.parse64UnitToLong("10");
        assertEquals(longValue, 64);
        longValue = NumberUtils.parse64UnitToLong("1~");
        assertEquals(longValue, 127);
        longValue = NumberUtils.parse64UnitToLong("20");
        assertEquals(longValue, 128);

        longValue = NumberUtils.parse64UnitToLong("-1");
        assertEquals(longValue, -1);
        longValue = NumberUtils.parse64UnitToLong("-a");
        assertEquals(longValue, -10);
        longValue = NumberUtils.parse64UnitToLong("-Y");
        assertEquals(longValue, -60);
        longValue = NumberUtils.parse64UnitToLong("-Z");
        assertEquals(longValue, -61);
        longValue = NumberUtils.parse64UnitToLong("-_");
        assertEquals(longValue, -62);
        longValue = NumberUtils.parse64UnitToLong("-~");
        assertEquals(longValue, -63);
        longValue = NumberUtils.parse64UnitToLong("-10");
        assertEquals(longValue, -64);
        longValue = NumberUtils.parse64UnitToLong("-1~");
        assertEquals(longValue, -127);
        longValue = NumberUtils.parse64UnitToLong("-20");
        assertEquals(longValue, -128);
    }

    @Test
    public void testParseString93() {
        BigInteger bi = NumberUtils.parse93Unit("1");
        assertEquals(bi.intValue(), 1);
        bi = NumberUtils.parse93Unit("a");
        assertEquals(bi.intValue(), 10);
        bi = NumberUtils.parse93Unit("Y");
        assertEquals(bi.intValue(), 60);
        bi = NumberUtils.parse93Unit("Z");
        assertEquals(bi.intValue(), 61);
        bi = NumberUtils.parse93Unit("_");
        assertEquals(bi.intValue(), 62);
        bi = NumberUtils.parse93Unit("~");
        assertEquals(bi.intValue(), 63);
        bi = NumberUtils.parse93Unit("!");
        assertEquals(bi.intValue(), 64);
        bi = NumberUtils.parse93Unit("|");
        assertEquals(bi.intValue(), 91);
        bi = NumberUtils.parse93Unit("}");
        assertEquals(bi.intValue(), 92);
        bi = NumberUtils.parse93Unit("10");
        assertEquals(bi.intValue(), 93);
        bi = NumberUtils.parse93Unit("1|");
        assertEquals(bi.intValue(), 184);
        bi = NumberUtils.parse93Unit("1}");
        assertEquals(bi.intValue(), 185);
        bi = NumberUtils.parse93Unit("20");
        assertEquals(bi.intValue(), 186);

        bi = NumberUtils.parse93Unit("-1");
        assertEquals(bi.intValue(), -1);
        bi = NumberUtils.parse93Unit("-a");
        assertEquals(bi.intValue(), -10);
        bi = NumberUtils.parse93Unit("-Y");
        assertEquals(bi.intValue(), -60);
        bi = NumberUtils.parse93Unit("-Z");
        assertEquals(bi.intValue(), -61);
        bi = NumberUtils.parse93Unit("-_");
        assertEquals(bi.intValue(), -62);
        bi = NumberUtils.parse93Unit("-~");
        assertEquals(bi.intValue(), -63);
        bi = NumberUtils.parse93Unit("-!");
        assertEquals(bi.intValue(), -64);
        bi = NumberUtils.parse93Unit("-|");
        assertEquals(bi.intValue(), -91);
        bi = NumberUtils.parse93Unit("-}");
        assertEquals(bi.intValue(), -92);
        bi = NumberUtils.parse93Unit("-10");
        assertEquals(bi.intValue(), -93);
        bi = NumberUtils.parse93Unit("-1|");
        assertEquals(bi.intValue(), -184);
        bi = NumberUtils.parse93Unit("-1}");
        assertEquals(bi.intValue(), -185);
        bi = NumberUtils.parse93Unit("-20");
        assertEquals(bi.intValue(), -186);

        long longValue = NumberUtils.parse93UnitToLong("1");
        assertEquals(longValue, 1);
        longValue = NumberUtils.parse93UnitToLong("a");
        assertEquals(longValue, 10);
        longValue = NumberUtils.parse93UnitToLong("Y");
        assertEquals(longValue, 60);
        longValue = NumberUtils.parse93UnitToLong("Z");
        assertEquals(longValue, 61);
        longValue = NumberUtils.parse93UnitToLong("_");
        assertEquals(longValue, 62);
        longValue = NumberUtils.parse93UnitToLong("~");
        assertEquals(longValue, 63);
        longValue = NumberUtils.parse93UnitToLong("!");
        assertEquals(longValue, 64);
        longValue = NumberUtils.parse93UnitToLong("|");
        assertEquals(longValue, 91);
        longValue = NumberUtils.parse93UnitToLong("}");
        assertEquals(longValue, 92);
        longValue = NumberUtils.parse93UnitToLong("10");
        assertEquals(longValue, 93);
        longValue = NumberUtils.parse93UnitToLong("1|");
        assertEquals(longValue, 184);
        longValue = NumberUtils.parse93UnitToLong("1}");
        assertEquals(longValue, 185);
        longValue = NumberUtils.parse93UnitToLong("20");
        assertEquals(longValue, 186);

        longValue = NumberUtils.parse93UnitToLong("-1");
        assertEquals(longValue, -1);
        longValue = NumberUtils.parse93UnitToLong("-a");
        assertEquals(longValue, -10);
        longValue = NumberUtils.parse93UnitToLong("-Y");
        assertEquals(longValue, -60);
        longValue = NumberUtils.parse93UnitToLong("-Z");
        assertEquals(longValue, -61);
        longValue = NumberUtils.parse93UnitToLong("-_");
        assertEquals(longValue, -62);
        longValue = NumberUtils.parse93UnitToLong("-~");
        assertEquals(longValue, -63);
        longValue = NumberUtils.parse93UnitToLong("-!");
        assertEquals(longValue, -64);
        longValue = NumberUtils.parse93UnitToLong("-|");
        assertEquals(longValue, -91);
        longValue = NumberUtils.parse93UnitToLong("-}");
        assertEquals(longValue, -92);
        longValue = NumberUtils.parse93UnitToLong("-10");
        assertEquals(longValue, -93);
        longValue = NumberUtils.parse93UnitToLong("-1|");
        assertEquals(longValue, -184);
        longValue = NumberUtils.parse93UnitToLong("-1}");
        assertEquals(longValue, -185);
        longValue = NumberUtils.parse93UnitToLong("-20");
        assertEquals(longValue, -186);
    }

    @Test
    public void testParseString128() {
        BigInteger bi = NumberUtils.parse128Unit("1");
        assertEquals(bi.intValue(), 1);
        bi = NumberUtils.parse128Unit("a");
        assertEquals(bi.intValue(), 10);
        bi = NumberUtils.parse128Unit("Y");
        assertEquals(bi.intValue(), 60);
        bi = NumberUtils.parse128Unit("Z");
        assertEquals(bi.intValue(), 61);
        bi = NumberUtils.parse128Unit("_");
        assertEquals(bi.intValue(), 62);
        bi = NumberUtils.parse128Unit("~");
        assertEquals(bi.intValue(), 63);
        bi = NumberUtils.parse128Unit("!");
        assertEquals(bi.intValue(), 64);
        bi = NumberUtils.parse128Unit("\"");
        assertEquals(bi.intValue(), 65);
        bi = NumberUtils.parse128Unit("¯");
        assertEquals(bi.intValue(), 126);
        bi = NumberUtils.parse128Unit("®");
        assertEquals(bi.intValue(), 127);
        bi = NumberUtils.parse128Unit("10");
        assertEquals(bi.intValue(), 128);
        bi = NumberUtils.parse128Unit("1®");
        assertEquals(bi.intValue(), 255);
        bi = NumberUtils.parse128Unit("20");
        assertEquals(bi.intValue(), 256);

        bi = NumberUtils.parse128Unit("-1");
        assertEquals(bi.intValue(), -1);
        bi = NumberUtils.parse128Unit("-a");
        assertEquals(bi.intValue(), -10);
        bi = NumberUtils.parse128Unit("-Y");
        assertEquals(bi.intValue(), -60);
        bi = NumberUtils.parse128Unit("-Z");
        assertEquals(bi.intValue(), -61);
        bi = NumberUtils.parse128Unit("-_");
        assertEquals(bi.intValue(), -62);
        bi = NumberUtils.parse128Unit("-~");
        assertEquals(bi.intValue(), -63);
        bi = NumberUtils.parse128Unit("-!");
        assertEquals(bi.intValue(), -64);
        bi = NumberUtils.parse128Unit("-\"");
        assertEquals(bi.intValue(), -65);
        bi = NumberUtils.parse128Unit("-¯");
        assertEquals(bi.intValue(), -126);
        bi = NumberUtils.parse128Unit("-®");
        assertEquals(bi.intValue(), -127);
        bi = NumberUtils.parse128Unit("-10");
        assertEquals(bi.intValue(), -128);
        bi = NumberUtils.parse128Unit("-1®");
        assertEquals(bi.intValue(), -255);
        bi = NumberUtils.parse128Unit("-20");
        assertEquals(bi.intValue(), -256);

        long longValue = NumberUtils.parse128UnitToLong("1");
        assertEquals(longValue, 1);
        longValue = NumberUtils.parse128UnitToLong("a");
        assertEquals(longValue, 10);
        longValue = NumberUtils.parse128UnitToLong("Y");
        assertEquals(longValue, 60);
        longValue = NumberUtils.parse128UnitToLong("Z");
        assertEquals(longValue, 61);
        longValue = NumberUtils.parse128UnitToLong("_");
        assertEquals(longValue, 62);
        longValue = NumberUtils.parse128UnitToLong("~");
        assertEquals(longValue, 63);
        longValue = NumberUtils.parse128UnitToLong("!");
        assertEquals(longValue, 64);
        longValue = NumberUtils.parse128UnitToLong("\"");
        assertEquals(longValue, 65);
        longValue = NumberUtils.parse128UnitToLong("¯");
        assertEquals(longValue, 126);
        longValue = NumberUtils.parse128UnitToLong("®");
        assertEquals(longValue, 127);
        longValue = NumberUtils.parse128UnitToLong("10");
        assertEquals(longValue, 128);
        longValue = NumberUtils.parse128UnitToLong("1®");
        assertEquals(longValue, 255);
        longValue = NumberUtils.parse128UnitToLong("20");
        assertEquals(longValue, 256);

        longValue = NumberUtils.parse128UnitToLong("-1");
        assertEquals(longValue, -1);
        longValue = NumberUtils.parse128UnitToLong("-a");
        assertEquals(longValue, -10);
        longValue = NumberUtils.parse128UnitToLong("-Y");
        assertEquals(longValue, -60);
        longValue = NumberUtils.parse128UnitToLong("-Z");
        assertEquals(longValue, -61);
        longValue = NumberUtils.parse128UnitToLong("-_");
        assertEquals(longValue, -62);
        longValue = NumberUtils.parse128UnitToLong("-~");
        assertEquals(longValue, -63);

        longValue = NumberUtils.parse128UnitToLong("-!");
        assertEquals(longValue, -64);
        longValue = NumberUtils.parse128UnitToLong("-\"");
        assertEquals(longValue, -65);
        longValue = NumberUtils.parse128UnitToLong("-¯");
        assertEquals(longValue, -126);
        longValue = NumberUtils.parse128UnitToLong("-®");
        assertEquals(longValue, -127);
        longValue = NumberUtils.parse128UnitToLong("-10");
        assertEquals(longValue, -128);
        longValue = NumberUtils.parse128UnitToLong("-1®");
        assertEquals(longValue, -255);
        longValue = NumberUtils.parse128UnitToLong("-20");
        assertEquals(longValue, -256);
    }

    public static void p64(long l) {
        System.out.println(l);
        System.out.println(String.valueOf(l).length());
        System.out.println(NumberUtils.toString64Unit(l));
        System.out.println(NumberUtils.toString64Unit(l).length());
        System.out.println(NumberUtils.toString64Unit(l - 1));
    }

    public static void p128(long l) {
        System.out.println(l);
        System.out.println(String.valueOf(l).length());
        System.out.println(NumberUtils.toString128Unit(l));
        System.out.println(NumberUtils.toString128Unit(l).length());
        System.out.println(NumberUtils.toString128Unit(l - 1));
    }

    static char[] cs = new char[] { '©', 'ß', '¿', '£', '¤', '¥', '¦', '§', 'µ', '¶', 'Â', 'Ä', 'Æ', 'Ê', 'Ë', 'Î', 'Ï',
        'Ð', 'Ñ', 'Ô', 'Ö', 'Û', 'Ü', 'Ý', 'â', 'ä', 'æ', 'ê', 'ë', 'î', 'ð', 'ñ', '¯', '®' };

    public static void main(String[] args) {
        System.out.println("64 : Long.MAX_VALUE");
        p64(Long.MAX_VALUE);

        System.out.println("64 : System.currentTimeMillis()");
        p64(System.currentTimeMillis());

        System.out.println("128 : Long.MAX_VALUE");
        p128(Long.MAX_VALUE);

        System.out.println("128 : System.currentTimeMillis()");
        p128(System.currentTimeMillis());

        System.out.println("\ntoString64Unit\n");
        int i = 1205971917;
        String s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);
        i = 924271465;
        s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);
        i = 536887567;
        s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);

        System.out.println("\ntoString93Unit\n");
        i = 1205971917;
        s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);
        i = 924271465;
        s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);
        i = 536887567;
        s = NumberUtils.toString64Unit(i);
        System.out.println(s);
        System.out.println(i);

        System.out.println(cs.length);

        //        for (int i = 0; i < 256; i++) {
        //            System.out.println(NumberUtils.toString128Unit(i));
        //        }

        //        System.out.println("ascii:");
        //        for (int i = 0; i < 255; i++) {
        //            System.out.println((char) i);
        //        }
    }

    //    !
    //    "
    //    #
    //    $
    //    %
    //    &
    //    '
    //    (
    //    )
    //    *
    //    +
    //    ,
    //    -
    //    .
    //    /
    //    0
    //    1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    :
    //    ;
    //    <
    //    =
    //    >
    //    ?
    //    @
    //    A
    //    B
    //    C
    //    D
    //    E
    //    F
    //    G
    //    H
    //    I
    //    J
    //    K
    //    L
    //    M
    //    N
    //    O
    //    P
    //    Q
    //    R
    //    S
    //    T
    //    U
    //    V
    //    W
    //    X
    //    Y
    //    Z
    //    [
    //    \
    //    ]
    //    ^
    //    _
    //    `
    //    a
    //    b
    //    c
    //    d
    //    e
    //    f
    //    g
    //    h
    //    i
    //    j
    //    k
    //    l
    //    m
    //    n
    //    o
    //    p
    //    q
    //    r
    //    s
    //    t
    //    u
    //    v
    //    w
    //    x
    //    y
    //    z
    //    {
    //    |
    //    }
    //    ~
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //    
    //     
    //    ¡

    //    ¨

    //    ª

    //    «

    //    ¬

    //    °
    //    ±

    //    ´

    //    ·
    //    ¸
    //    ¹
    //    º
    //    »
    //    ¼
    //    ½
    //    ¾

    //  ©
    //  ß
    //  ¿
    //  £
    //  ¤
    //  ¥
    //  ¦
    //  §
    //  µ
    //  ¶
    //    À
    //    Á
    //    Â
    //    Ä
    //    Å
    //    Æ
    //    È
    //    É
    //    Ê
    //    Ë
    //    Ì
    //    Í
    //    Î
    //    Ï
    //    Ð
    //    Ñ
    //    Ò
    //    Ó
    //    Ô
    //    Õ
    //    Ö
    //    Ù
    //    Ú
    //    Û
    //    Ü
    //    Ý
    //    à
    //    á
    //    â
    //    ä
    //    å
    //    æ
    //    è
    //    é
    //    ê
    //    ë
    //    ì
    //    í
    //    î
    //    ï
    //    ð
    //    ñ
    //    ò
    //    ó
    //    ô
    //    õ
    //    ö
    //    ù
    //    ú
    //    û
    //    ü
    //    ý
    //  ¯
    //  ®

    //  Ø
    //  ø

    //    Þ
    //    þ
}
