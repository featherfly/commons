
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:49:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import org.testng.annotations.Test;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class UserAccessorWithReflectionTest extends UserAccessorTest {

    @Test
    public void setReflection() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty(0).setValue(u, "yi");
            bd.getBeanProperty(1).setValue(u, 18);
            bd.getBeanProperty(2).setValue(u, "yufei");
            bd.getBeanProperty(3).setValue(u, "MALE");
        }
    }

    @Test
    public void getReflection() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty(0).getValue(u);
            bd.getBeanProperty(1).getValue(u);
            bd.getBeanProperty(2).getValue(u);
            bd.getBeanProperty(3).getValue(u);
        }
    }

    @Test
    public void setReflectionByName() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty("name").setValue(u, "yi");
            bd.getBeanProperty("age").setValue(u, 18);
            bd.getBeanProperty("username").setValue(u, "yufei");
            bd.getBeanProperty("gender").setValue(u, "MALE");
        }
    }

    @Test
    public void getReflectionByName() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty("name").getValue(u);
            bd.getBeanProperty("age").getValue(u);
            bd.getBeanProperty("username").getValue(u);
            bd.getBeanProperty("gender").getValue(u);
        }
    }
}
