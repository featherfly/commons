
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.Test;

/**
 * <p>
 * NumberUtilsTest
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
        assertEquals(string64, "@");
        string64 = NumberUtils.toString64Unit(64);
        assertEquals(string64, "10");
        string64 = NumberUtils.toString64Unit(65);
        assertEquals(string64, "11");
        string64 = NumberUtils.toString64Unit(126);
        assertEquals(string64, "1_");
        string64 = NumberUtils.toString64Unit(127);
        assertEquals(string64, "1@");
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
        assertEquals(string64, "-@");
        string64 = NumberUtils.toString64Unit(-64);
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString64Unit(-65);
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString64Unit(-126);
        assertEquals(string64, "-1_");
        string64 = NumberUtils.toString64Unit(-127);
        assertEquals(string64, "-1@");
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
        assertEquals(string64, "@");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(64));
        assertEquals(string64, "10");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(65));
        assertEquals(string64, "11");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(127));
        assertEquals(string64, "1@");
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
        assertEquals(string64, "-@");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-64));
        assertEquals(string64, "-10");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-65));
        assertEquals(string64, "-11");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-127));
        assertEquals(string64, "-1@");
        string64 = NumberUtils.toString64Unit(BigInteger.valueOf(-128));
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
        bi = NumberUtils.parse64Unit("@");
        assertEquals(bi.intValue(), 63);
        bi = NumberUtils.parse64Unit("10");
        assertEquals(bi.intValue(), 64);
        bi = NumberUtils.parse64Unit("1@");
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
        bi = NumberUtils.parse64Unit("-@");
        assertEquals(bi.intValue(), -63);
        bi = NumberUtils.parse64Unit("-10");
        assertEquals(bi.intValue(), -64);
        bi = NumberUtils.parse64Unit("-1@");
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
        longValue = NumberUtils.parse64UnitToLong("@");
        assertEquals(longValue, 63);
        longValue = NumberUtils.parse64UnitToLong("10");
        assertEquals(longValue, 64);
        longValue = NumberUtils.parse64UnitToLong("1@");
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
        longValue = NumberUtils.parse64UnitToLong("-@");
        assertEquals(longValue, -63);
        longValue = NumberUtils.parse64UnitToLong("-10");
        assertEquals(longValue, -64);
        longValue = NumberUtils.parse64UnitToLong("-1@");
        assertEquals(longValue, -127);
        longValue = NumberUtils.parse64UnitToLong("-20");
        assertEquals(longValue, -128);
    }
}
