
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:49:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanUtils;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class EmployeeAccessorWithReflectionTest extends EmployeeAccessorTest {

    @Test(groups = "set", priority = 30)
    public void setReflection() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty(0).setValue(e, Long.valueOf(i));
            bd.getBeanProperty(1).setValue(e, "name_");
            bd.getBeanProperty(2).setValue(e, date);
            bd.getBeanProperty(3).setValue(e, "MALE");
            bd.getBeanProperty(4).setValue(e, i);
            bd.getBeanProperty(5).setValue(e, i);
            bd.getBeanProperty(6).setValue(e, "idCard_");
            bd.getBeanProperty(7).setValue(e, "mobile_");
            bd.getBeanProperty(8).setValue(e, "email_");
            bd.getBeanProperty(9).setValue(e, "fax_");
            bd.getBeanProperty(10).setValue(e, "remark0_");
            bd.getBeanProperty(11).setValue(e, "remark1_");
            bd.getBeanProperty(12).setValue(e, "remark2_");
            bd.getBeanProperty(13).setValue(e, "remark3_");
            bd.getBeanProperty(14).setValue(e, "remark4_");
            bd.getBeanProperty(15).setValue(e, "remark5_");
            bd.getBeanProperty(16).setValue(e, "remark6_");
            bd.getBeanProperty(17).setValue(e, "remark7_");
            bd.getBeanProperty(18).setValue(e, "remark8_");
            bd.getBeanProperty(19).setValue(e, "remark9_");
        }
    }

    @Test(priority = 30, dependsOnGroups = "set")
    public void getReflection() {
        for (int i = 0; i < total; i++) {
            bd.getBeanProperty(0).getValue(e);
            bd.getBeanProperty(1).getValue(e);
            bd.getBeanProperty(2).getValue(e);
            bd.getBeanProperty(3).getValue(e);
            bd.getBeanProperty(4).getValue(e);
            bd.getBeanProperty(5).getValue(e);
            bd.getBeanProperty(6).getValue(e);
            bd.getBeanProperty(7).getValue(e);
            bd.getBeanProperty(8).getValue(e);
            bd.getBeanProperty(9).getValue(e);
            bd.getBeanProperty(10).getValue(e);
            bd.getBeanProperty(11).getValue(e);
            bd.getBeanProperty(12).getValue(e);
            bd.getBeanProperty(13).getValue(e);
            bd.getBeanProperty(14).getValue(e);
            bd.getBeanProperty(15).getValue(e);
            bd.getBeanProperty(16).getValue(e);
            bd.getBeanProperty(17).getValue(e);
            bd.getBeanProperty(18).getValue(e);
            bd.getBeanProperty(19).getValue(e);
        }
    }

    @Test(groups = "set", dependsOnMethods = "setReflection")
    public void setReflectionByName() {
        for (int i = 0; i < total; i++) {
            BeanUtils.setProperty(e, "id", Long.valueOf(i));
            BeanUtils.setProperty(e, "name", "name_");
            BeanUtils.setProperty(e, "birthDate", date);
            BeanUtils.setProperty(e, "gender", "MALE");
            BeanUtils.setProperty(e, "departmentId", i);
            BeanUtils.setProperty(e, "organId", i);
            BeanUtils.setProperty(e, "identityCard", "idCard_");
            BeanUtils.setProperty(e, "mobile", "mobile_");
            BeanUtils.setProperty(e, "email", "email_");
            BeanUtils.setProperty(e, "fax", "fax_");
            BeanUtils.setProperty(e, "remark0", "remark0_");
            BeanUtils.setProperty(e, "remark1", "remark1_");
            BeanUtils.setProperty(e, "remark2", "remark2_");
            BeanUtils.setProperty(e, "remark3", "remark3_");
            BeanUtils.setProperty(e, "remark4", "remark4_");
            BeanUtils.setProperty(e, "remark5", "remark5_");
            BeanUtils.setProperty(e, "remark6", "remark6_");
            BeanUtils.setProperty(e, "remark7", "remark7_");
            BeanUtils.setProperty(e, "remark8", "remark8_");
            BeanUtils.setProperty(e, "remark9", "remark9_");
        }
    }

    @Test(priority = 50, dependsOnGroups = "set")
    public void getReflectionByName() {
        for (int i = 0; i < total; i++) {
            BeanUtils.getProperty(e, "id");
            BeanUtils.getProperty(e, "name");
            BeanUtils.getProperty(e, "birthDate");
            BeanUtils.getProperty(e, "gender");
            BeanUtils.getProperty(e, "departmentId");
            BeanUtils.getProperty(e, "organId");
            BeanUtils.getProperty(e, "identityCard");
            BeanUtils.getProperty(e, "mobile");
            BeanUtils.getProperty(e, "email");
            BeanUtils.getProperty(e, "fax");
            BeanUtils.getProperty(e, "remark0");
            BeanUtils.getProperty(e, "remark1");
            BeanUtils.getProperty(e, "remark2");
            BeanUtils.getProperty(e, "remark3");
            BeanUtils.getProperty(e, "remark4");
            BeanUtils.getProperty(e, "remark5");
            BeanUtils.getProperty(e, "remark6");
            BeanUtils.getProperty(e, "remark7");
            BeanUtils.getProperty(e, "remark8");
            BeanUtils.getProperty(e, "remark9");
        }
    }
}
