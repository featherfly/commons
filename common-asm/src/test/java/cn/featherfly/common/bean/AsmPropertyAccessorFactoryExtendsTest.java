
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.BytesClassLoader;
import vo.Columns5;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactoryExtendsTest {

    AsmPropertyAccessorFactory factory;
    PropertyAccessor<Columns5> columnsPropertyAccessor;

    Columns5 columns;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        columnsPropertyAccessor = factory.create(Columns5.class);
        // after scan package and create which your wanted create, then invoke this
        factory.createPropertyAccessorCascade();
    }

    @BeforeMethod
    void b() {
        columns = new Columns5().setName1("name1");
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        final Integer id = 12345;
        columnsPropertyAccessor.setPropertyValue(columns, 4, id);

        assertEquals(columns.getId(), id);
    }

    @Test
    void getPropertyValueByName() throws Exception {
        final Integer id = 12345;
        columnsPropertyAccessor.setPropertyValue(columns, "id", id);

        assertEquals(columns.getId(), id);
    }
}
