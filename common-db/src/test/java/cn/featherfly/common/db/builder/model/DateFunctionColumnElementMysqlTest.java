
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.operator.DateFunction;

/**
 * ConditionColumnElementTest.
 *
 * @author zhongj
 */
public class DateFunctionColumnElementMysqlTest implements DateFunctionColumnElementTest {

    Dialect dialect = Dialects.mysql();

    ColumnElement c;

    String userTableAlias = "u";
    String userTableAlias2 = "u2";
    String username = "yufei";

    Integer value = 18;

    String createTimeColumn = "create_time";

    @BeforeMethod
    void beforeMethod() {
        c = null;
        userTableAlias = "u";
        userTableAlias2 = "u2";
        username = "yufei";
        value = 18;
    }

    void print(ColumnElement c) {
        System.out.println(c);
    }

    @Override
    @Test
    public void getYear() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_YEAR);
        print(c);

        assertEquals(c.toSql(), "year(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time", DateFunction.GET_YEAR);
        print(c);
        assertEquals(c.toSql(), "year(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getMonth() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_MONTH);
        print(c);

        assertEquals(c.toSql(), "month(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time", DateFunction.GET_MONTH);
        print(c);
        assertEquals(c.toSql(), "month(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getDayOfMonth() {
        c = new ColumnElement(dialect, "create_time",
            DateFunction.GET_DAY_OF_MONTH);
        print(c);

        assertEquals(c.toSql(), "dayofmonth(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_DAY_OF_MONTH);
        print(c);
        assertEquals(c.toSql(), "dayofmonth(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getHour() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_HOUR);
        print(c);

        assertEquals(c.toSql(), "hour(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time", DateFunction.GET_HOUR);
        print(c);
        assertEquals(c.toSql(), "hour(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getMinute() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_MINUTE);
        print(c);

        assertEquals(c.toSql(), "minute(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_MINUTE);
        print(c);
        assertEquals(c.toSql(), "minute(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getSecond() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_SECOND);
        print(c);

        assertEquals(c.toSql(), "second(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_SECOND);
        print(c);
        assertEquals(c.toSql(), "second(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getWeekday() {
        c = new ColumnElement(dialect, "create_time", DateFunction.GET_WEEKDAY);
        print(c);

        assertEquals(c.toSql(), "weekday(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_WEEKDAY);
        print(c);
        assertEquals(c.toSql(), "weekday(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getWeekOfYear() {
        c = new ColumnElement(dialect, "create_time",
            DateFunction.GET_WEEK_OF_YEAR);
        print(c);

        assertEquals(c.toSql(), "weekofyear(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_WEEK_OF_YEAR);
        print(c);
        assertEquals(c.toSql(), "weekofyear(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDayOfYear() {
        c = new ColumnElement(dialect, "create_time",
            DateFunction.GET_DAY_OF_YEAR);
        print(c);

        assertEquals(c.toSql(), "dayofyear(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_DAY_OF_YEAR);
        print(c);
        assertEquals(c.toSql(), "dayofyear(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getQuarter() {
        c = new ColumnElement(dialect, "create_time",
            DateFunction.GET_QUARTER);
        print(c);

        assertEquals(c.toSql(), "quarter(`create_time`)");
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.GET_QUARTER);
        print(c);
        assertEquals(c.toSql(), "quarter(u.`create_time`)");
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getDateFormat() {
        String format = "%Y-%m-%d %H:%i:%s";
        c = new ColumnElement(dialect, "create_time", DateFunction.DATE_FORMAT, format);
        print(c);

        assertEquals(c.toSql(), Str.format("date_format(`create_time`, '{}')", format));
        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.DATE_FORMAT, format);
        print(c);
        assertEquals(c.toSql(), Str.format("date_format(u.`create_time`, '{}')", format));
        //assertEquals(c.getParam(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void getTimeFormat() {
        String format = "%H:%i:%s";
        c = new ColumnElement(dialect, "create_time",
            DateFunction.TIME_FORMAT, format);
        print(c);

        assertEquals(c.toSql(), Str.format("time_format(`create_time`, '{}')", format));
        //        //assertEquals(c.getParam(), value);

        c = new ColumnElement(dialect, "u", "create_time",
            DateFunction.TIME_FORMAT, format);
        print(c);
        assertEquals(c.toSql(), Str.format("time_format(u.`create_time`, '{}')", format));
        //        //assertEquals(c.getParam(), value);
    }
}
