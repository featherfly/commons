
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

/**
 * ConditionColumnElementTest.
 *
 * @author zhongj
 */
public class ArithmeticColumnElementTest {

    ArithmeticColumnElement c;

    String userTableAlias = "u";
    String userTableAlias2 = "u2";
    String username = "yufei";
    Integer age = 18;

    String usernameColumn = "age";
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
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @Test
    void mysql() {
        Dialect dialect = Dialects.mysql();

        c = new ArithmeticColumnElement(dialect, "age");
        print(c);
        assertEquals(c.toSql(), "`age`");
        assertEquals(c.getParam(), new ArrayList<>());

        c = new ArithmeticColumnElement(dialect, "age", "u");
        print(c);
        assertEquals(c.toSql(), "u.`age`");
        assertEquals(c.getParam(), new ArrayList<>());

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, age);
        print(c);
        assertEquals(c.toSql(), "`age` + ?");
        assertEquals(c.getParam(), Lang.list(age));

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, age, "u");
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

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, ages[0], "u")
            .add(CalculationOperator.SUBTRACT, ages[1]).add(CalculationOperator.MULTIPLY, ages[2])
            .add(CalculationOperator.DIVIDE, ages[3]);
        print(c);
        assertEquals(c.toSql(), "u.`age` + ? - ? * ? / ?");
        assertEquals(c.getParam(), Lang.toList(ages));

    }
}
