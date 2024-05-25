
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
import vo.Columns1;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactorySetterReturnObjTest {

    AsmPropertyAccessorFactory factory;
    PropertyAccessor<Columns1> columnsPropertyAccessor;

    Columns1 columns;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        columnsPropertyAccessor = factory.create(Columns1.class);
        // after scan package and create which your wanted create, then invoke this
        factory.createPropertyAccessorCascade();
    }

    @BeforeMethod
    void b() {
        columns = new Columns1().setName1("name1");
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        final String name = "columns1_name";
        columnsPropertyAccessor.setPropertyValue(columns, 0, name);

        assertEquals(columns.getName1(), name);
    }

    @Test
    void getPropertyValueByName() throws Exception {
        final String name = "columns1_name";
        columnsPropertyAccessor.setPropertyValue(columns, "name1", name);

        assertEquals(columns.getName1(), name);
    }
}
