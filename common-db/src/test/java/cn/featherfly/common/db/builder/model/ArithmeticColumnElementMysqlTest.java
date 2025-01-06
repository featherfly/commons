
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.operator.CalculationOperator;
import cn.featherfly.common.operator.DateFunction;

/**
 * ConditionColumnElementTest.
 *
 * @author zhongj
 */
public class ArithmeticColumnElementMysqlTest implements ArithmeticColumnElementTest {

    final Dialect dialect = Dialects.mysql();

    ArithmeticColumnElement c;

    String userTableAlias = "u";
    String userTableAlias2 = "u2";
    String username = "yufei";
    Integer age = 18;
    Integer value = 1;

    String usernameColumn = "username";
    String ageColumn = "age";

    @BeforeMethod
    void beforeMethod() {
        c = null;
        userTableAlias = "u";
        userTableAlias2 = "u2";
        username = "yufei";
        age = 18;
    }

    void print(ArithmeticColumnElement c) {
        System.out.println();
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @Override
    @Test
    public void calculationParameter() {
        c = new ArithmeticColumnElement(dialect, "age");
        print(c);
        assertEquals(c.toSql(), "`age`");
        assertEquals(c.getParam(), new ArrayList<>());

        c = new ArithmeticColumnElement(dialect, "u", "age");
        print(c);
        assertEquals(c.toSql(), "u.`age`");
        assertEquals(c.getParam(), new ArrayList<>());

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, age);
        print(c);
        assertEquals(c.toSql(), "`age` + ?");
        assertEquals(c.getParam(), Lang.list(age));

        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS, age);
        print(c);
        assertEquals(c.toSql(), "u.`age` + ?");
        assertEquals(c.getParam(), Lang.list(age));

        // ----------------------------------------------------------------------------------------------------------------

        Object[] ages = new Object[] { 1, 2, 3, 4 };

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, ages[0])
            .add(CalculationOperator.SUBTRACT, ages[1]).add(CalculationOperator.MULTIPLY, ages[2])
            .add(CalculationOperator.DIVIDE, ages[3]);
        print(c);
        assertEquals(c.toSql(), "`age` + ? - ? * ? / ?");
        assertEquals(c.getParam(), Lang.toList(ages));

        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS, ages[0])
            .add(CalculationOperator.SUBTRACT, ages[1]).add(CalculationOperator.MULTIPLY, ages[2])
            .add(CalculationOperator.DIVIDE, ages[3]);
        print(c);
        assertEquals(c.toSql(), "u.`age` + ? - ? * ? / ?");
        assertEquals(c.getParam(), Lang.toList(ages));

    }

    @Override
    @Test
    public void calculationColumn() {
        ColumnElement cc = new ColumnElement(dialect, "username");
        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS,
            cc);

        print(c);
        assertEquals(c.toSql(), "`age` + `username`");
        assertEquals(c.getParam(), Lang.list(cc));

        cc = new ColumnElement(dialect, "u2", "username");
        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS,
            new ColumnElement(dialect, "u2", "username"));
        print(c);
        assertEquals(c.toSql(), "u.`age` + u2.`username`");
        assertEquals(c.getParam(), Lang.list(cc));

        // ----------------------------------------------------------------------------------------------------------------

        ColumnElement cc0 = new ColumnElement(dialect, "age0");
        ColumnElement cc1 = new ColumnElement(dialect, "age1");
        ColumnElement cc2 = new ColumnElement(dialect, "age2");
        ColumnElement cc3 = new ColumnElement(dialect, "age3");
        Object[] argus = new Object[] { cc0, cc1, cc2, cc3, 1, 2, 3, 4 };

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS,
            cc0)
            .add(CalculationOperator.SUBTRACT, cc1) //
            .add(CalculationOperator.MULTIPLY, cc2) //
            .add(CalculationOperator.DIVIDE, cc3) //
            .add(CalculationOperator.PLUS, argus[0 + 4]) //
            .add(CalculationOperator.SUBTRACT, argus[1 + 4]) //
            .add(CalculationOperator.MULTIPLY, argus[2 + 4]) //
            .add(CalculationOperator.DIVIDE, argus[3 + 4]);

        print(c);
        assertEquals(c.toSql(), "`age` + `age0` - `age1` * `age2` / `age3` + ? - ? * ? / ?");
        assertEquals(c.getParam(), Lang.toList(argus));

        cc0 = new ColumnElement(dialect, "u0", "age0");
        cc1 = new ColumnElement(dialect, "u1", "age1");
        cc2 = new ColumnElement(dialect, "u2", "age2");
        cc3 = new ColumnElement(dialect, "u3", "age3");
        argus = new Object[] { cc0, cc1, cc2, cc3, 1, 2, 3, 4 };
        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS,
            cc0)
            .add(CalculationOperator.SUBTRACT, cc1) //
            .add(CalculationOperator.MULTIPLY, cc2) //
            .add(CalculationOperator.DIVIDE, cc3) //
            .add(CalculationOperator.PLUS, argus[0 + 4]) //
            .add(CalculationOperator.SUBTRACT, argus[1 + 4]) //
            .add(CalculationOperator.MULTIPLY, argus[2 + 4]) //
            .add(CalculationOperator.DIVIDE, argus[3 + 4]);
        print(c);
        assertEquals(c.toSql(), "u.`age` + u0.`age0` - u1.`age1` * u2.`age2` / u3.`age3` + ? - ? * ? / ?");
        assertEquals(c.getParam(), Lang.toList(argus));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void functionCalculation() {
        ColumnElement columnElement = new ColumnElement(dialect, "create_time", DateFunction.GET_YEAR);
        c = columnElement.operate(CalculationOperator.PLUS, value);

        print(c);
        assertEquals(c.toSql(), "year(`create_time`) + ?");

        c.add(CalculationOperator.SUBTRACT, value);

        print(c);
        assertEquals(c.toSql(), "year(`create_time`) + ? - ?");

        // ----------------------------------------------------------------------------------------------------------------

        columnElement = new ColumnElement(dialect, "u", "create_time", DateFunction.GET_YEAR);
        c = columnElement.operate(CalculationOperator.PLUS, value);

        print(c);
        assertEquals(c.toSql(), "year(u.`create_time`) + ?");

        c.add(CalculationOperator.SUBTRACT, value);

        print(c);
        assertEquals(c.toSql(), "year(u.`create_time`) + ? - ?");
    }
}
