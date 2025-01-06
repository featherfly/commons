
package cn.featherfly.common.db.builder.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.db.SqlUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialects;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.operator.CalculationOperator;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.repository.IgnoreStrategy;

/**
 * ConditionColumnElementTest.
 *
 * @author zhongj
 */
public class CompositeConditionColumnElementTest {

    CompositeConditionColumnElement composite;

    String userTableAlias = "u";
    String userTableAlias2 = "u2";
    String username = "yufei";
    Integer age = 18;

    String usernameColumn = "age";
    String ageColumn = "age";

    @BeforeMethod
    void beforeMethod() {
        composite = null;
        userTableAlias = "u";
        userTableAlias2 = "u2";
        username = "yufei";
        age = 18;
    }

    void print(CompositeConditionColumnElement c) {
        System.out.println(c);
        System.out.println(ArrayUtils.toString(c.getParam()));
    }

    @Test
    void mysql() {
        Dialect dialect = Dialects.mysql();
        int add = 5;

        ArithmeticColumnElement c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, add);

        composite = new CompositeConditionColumnElement(dialect, c, age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);

        print(composite);
        assertEquals(composite.toSql(), "`age` + ? < ?");
        assertEquals(composite.getParam(), new Object[] { add, age });

        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS, add);

        composite = new CompositeConditionColumnElement(dialect, c, age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);

        print(composite);
        assertEquals(composite.toSql(), "u.`age` + ? < ?");
        assertEquals(composite.getParam(), new Object[] { add, age });

        // ----------------------------------------------------------------------------------------------------------------

        Object[] actAges = new Object[] { 1, 2, 3, 4 };

        c = new ArithmeticColumnElement(dialect, "age", CalculationOperator.PLUS, actAges[0])
            .add(CalculationOperator.SUBTRACT, actAges[1]).add(CalculationOperator.MULTIPLY, actAges[2])
            .add(CalculationOperator.DIVIDE, actAges[3]);

        composite = new CompositeConditionColumnElement(dialect, c, age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);

        print(composite);
        assertEquals(composite.toSql(), "`age` + ? - ? * ? / ? < ?");
        assertEquals(composite.getParam(), SqlUtils.flatParams(actAges, age));

        c = new ArithmeticColumnElement(dialect, "u", "age", CalculationOperator.PLUS, actAges[0])
            .add(CalculationOperator.SUBTRACT, actAges[1]).add(CalculationOperator.MULTIPLY, actAges[2])
            .add(CalculationOperator.DIVIDE, actAges[3]);

        composite = new CompositeConditionColumnElement(dialect, c, age, ComparisonOperator.LT, IgnoreStrategy.EMPTY);

        print(composite);
        assertEquals(composite.toSql(), "u.`age` + ? - ? * ? / ? < ?");
        assertEquals(composite.getParam(), SqlUtils.flatParams(actAges, age));
    }
}
