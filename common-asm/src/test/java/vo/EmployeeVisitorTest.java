
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:49:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.time.LocalDate;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanUtils;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class EmployeeVisitorTest {

    int max = 10000000;

    EmployeeVisitor ev = new EmployeeVisitor();
    Employee e = new Employee();

    LocalDate date = LocalDate.now();

    BeanDescriptor<Employee> bd = BeanDescriptor.getBeanDescriptor(Employee.class);

    @BeforeClass
    void b() {
    }

    @AfterMethod
    void am() {
        System.out.println("id: " + e.getId());
        System.out.println("name: " + e.getName());
        System.out.println("gender: " + e.getGender());
    }

    @Test(groups = "set")
    public void set() {
        for (int i = 0; i < max; i++) {
            e.setId(Long.valueOf(i));
            e.setName("name_" + i);
            e.setBirthDate(date);
            e.setGender("MALE");
            e.setDepartmentId(i);
            e.setOrganId(i);
            e.setIdentityCard("idCard_" + i);
            e.setMobile("mobile_" + i);
            e.setEmail("email_" + i);
            e.setFax("fax_" + i);
            e.setRemark0("remark0_" + i);
            e.setRemark1("remark1_" + i);
            e.setRemark2("remark2_" + i);
            e.setRemark3("remark3_" + i);
            e.setRemark4("remark4_" + i);
            e.setRemark5("remark5_" + i);
            e.setRemark6("remark6_" + i);
            e.setRemark7("remark7_" + i);
            e.setRemark8("remark8_" + i);
            e.setRemark9("remark9_" + i);
        }
    }

    @Test(priority = 0, dependsOnGroups = "set")
    public void get() {
        for (int i = 0; i < max; i++) {
            e.getId();
            e.getName();
            e.getBirthDate();
            e.getGender();
            e.getDepartmentId();
            e.getOrganId();
            e.getIdentityCard();
            e.getMobile();
            e.getEmail();
            e.getFax();
            e.getRemark0();
            e.getRemark1();
            e.getRemark2();
            e.getRemark3();
            e.getRemark4();
            e.getRemark5();
            e.getRemark6();
            e.getRemark7();
            e.getRemark8();
            e.getRemark9();
        }
    }

    @Test(groups = "set", dependsOnMethods = "set")
    public void setVisitor() {
        for (int i = 0; i < max; i++) {
            ev.setProperty(e, 0, Long.valueOf(i));
            ev.setProperty(e, 1, "name_" + i);
            ev.setProperty(e, 2, date);
            ev.setProperty(e, 3, "MALE");
            ev.setProperty(e, 4, i);
            ev.setProperty(e, 5, i);
            ev.setProperty(e, 6, "idCard_" + i);
            ev.setProperty(e, 7, "mobile_" + i);
            ev.setProperty(e, 8, "email_" + i);
            ev.setProperty(e, 9, "fax_" + i);
            ev.setProperty(e, 10, "remark0_" + i);
            ev.setProperty(e, 11, "remark1_" + i);
            ev.setProperty(e, 12, "remark2_" + i);
            ev.setProperty(e, 13, "remark3_" + i);
            ev.setProperty(e, 14, "remark4_" + i);
            ev.setProperty(e, 15, "remark5_" + i);
            ev.setProperty(e, 16, "remark6_" + i);
            ev.setProperty(e, 17, "remark7_" + i);
            ev.setProperty(e, 18, "remark8_" + i);
            ev.setProperty(e, 19, "remark9_" + i);
        }
    }

    @Test(priority = 1, dependsOnGroups = "set")
    public void getVisitor() {
        for (int i = 0; i < max; i++) {
            ev.getProperty(e, 0);
            ev.getProperty(e, 1);
            ev.getProperty(e, 2);
            ev.getProperty(e, 3);
            ev.getProperty(e, 4);
            ev.getProperty(e, 5);
            ev.getProperty(e, 6);
            ev.getProperty(e, 7);
            ev.getProperty(e, 8);
            ev.getProperty(e, 9);
            ev.getProperty(e, 10);
            ev.getProperty(e, 11);
            ev.getProperty(e, 12);
            ev.getProperty(e, 13);
            ev.getProperty(e, 14);
            ev.getProperty(e, 15);
            ev.getProperty(e, 16);
            ev.getProperty(e, 17);
            ev.getProperty(e, 18);
            ev.getProperty(e, 19);
        }
    }

    @Test(groups = "set", dependsOnMethods = "setVisitor")
    public void setVisitorByName() {
        for (int i = 0; i < max; i++) {
            ev.setProperty(e, "id", Long.valueOf(i));
            ev.setProperty(e, "name", "name_" + i);
            ev.setProperty(e, "birthDate", date);
            ev.setProperty(e, "gender", "MALE");
            ev.setProperty(e, "departmentId", i);
            ev.setProperty(e, "organId", i);
            ev.setProperty(e, "identityCard", "idCard_" + i);
            ev.setProperty(e, "mobile", "mobile_" + i);
            ev.setProperty(e, "email", "email_" + i);
            ev.setProperty(e, "fax", "fax_" + i);
            ev.setProperty(e, "remark0", "remark0_" + i);
            ev.setProperty(e, "remark1", "remark1_" + i);
            ev.setProperty(e, "remark2", "remark2_" + i);
            ev.setProperty(e, "remark3", "remark3_" + i);
            ev.setProperty(e, "remark4", "remark4_" + i);
            ev.setProperty(e, "remark5", "remark5_" + i);
            ev.setProperty(e, "remark6", "remark6_" + i);
            ev.setProperty(e, "remark7", "remark7_" + i);
            ev.setProperty(e, "remark8", "remark8_" + i);
            ev.setProperty(e, "remark9", "remark9_" + i);
        }
    }

    @Test(priority = 2, dependsOnGroups = "set")
    public void getVisitorByName() {
        for (int i = 0; i < max; i++) {
            ev.getProperty(e, "id");
            ev.getProperty(e, "name");
            ev.getProperty(e, "birthDate");
            ev.getProperty(e, "gender");
            ev.getProperty(e, "departmentId");
            ev.getProperty(e, "organId");
            ev.getProperty(e, "identityCard");
            ev.getProperty(e, "mobile");
            ev.getProperty(e, "email");
            ev.getProperty(e, "fax");
            ev.getProperty(e, "remark0");
            ev.getProperty(e, "remark1");
            ev.getProperty(e, "remark2");
            ev.getProperty(e, "remark3");
            ev.getProperty(e, "remark4");
            ev.getProperty(e, "remark5");
            ev.getProperty(e, "remark6");
            ev.getProperty(e, "remark7");
            ev.getProperty(e, "remark8");
            ev.getProperty(e, "remark9");
        }
    }

    @Test(groups = "set", dependsOnMethods = "setVisitorByName")
    public void setReflection() {
        for (int i = 0; i < max; i++) {
            bd.getBeanProperty(0).setValue(e, Long.valueOf(i));
            bd.getBeanProperty(1).setValue(e, "name_" + i);
            bd.getBeanProperty(2).setValue(e, date);
            bd.getBeanProperty(3).setValue(e, "MALE");
            bd.getBeanProperty(4).setValue(e, i);
            bd.getBeanProperty(5).setValue(e, i);
            bd.getBeanProperty(6).setValue(e, "idCard_" + i);
            bd.getBeanProperty(7).setValue(e, "mobile_" + i);
            bd.getBeanProperty(8).setValue(e, "email_" + i);
            bd.getBeanProperty(9).setValue(e, "fax_" + i);
            bd.getBeanProperty(10).setValue(e, "remark0_" + i);
            bd.getBeanProperty(11).setValue(e, "remark1_" + i);
            bd.getBeanProperty(12).setValue(e, "remark2_" + i);
            bd.getBeanProperty(13).setValue(e, "remark3_" + i);
            bd.getBeanProperty(14).setValue(e, "remark4_" + i);
            bd.getBeanProperty(15).setValue(e, "remark5_" + i);
            bd.getBeanProperty(16).setValue(e, "remark6_" + i);
            bd.getBeanProperty(17).setValue(e, "remark7_" + i);
            bd.getBeanProperty(18).setValue(e, "remark8_" + i);
            bd.getBeanProperty(19).setValue(e, "remark9_" + i);
        }
    }

    @Test(priority = 30, dependsOnGroups = "set")
    public void getReflection() {
        for (int i = 0; i < max; i++) {
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

    @Test(priority = 40, dependsOnMethods = "setReflection")
    public void setReflectionByName() {
        for (int i = 0; i < max; i++) {
            BeanUtils.setProperty(e, "id", Long.valueOf(i));
            BeanUtils.setProperty(e, "name", "name_" + i);
            BeanUtils.setProperty(e, "birthDate", date);
            BeanUtils.setProperty(e, "gender", "MALE");
            BeanUtils.setProperty(e, "departmentId", i);
            BeanUtils.setProperty(e, "organId", i);
            BeanUtils.setProperty(e, "identityCard", "idCard_" + i);
            BeanUtils.setProperty(e, "mobile", "mobile_" + i);
            BeanUtils.setProperty(e, "email", "email_" + i);
            BeanUtils.setProperty(e, "fax", "fax_" + i);
            BeanUtils.setProperty(e, "remark0", "remark0_" + i);
            BeanUtils.setProperty(e, "remark1", "remark1_" + i);
            BeanUtils.setProperty(e, "remark2", "remark2_" + i);
            BeanUtils.setProperty(e, "remark3", "remark3_" + i);
            BeanUtils.setProperty(e, "remark4", "remark4_" + i);
            BeanUtils.setProperty(e, "remark5", "remark5_" + i);
            BeanUtils.setProperty(e, "remark6", "remark6_" + i);
            BeanUtils.setProperty(e, "remark7", "remark7_" + i);
            BeanUtils.setProperty(e, "remark8", "remark8_" + i);
            BeanUtils.setProperty(e, "remark9", "remark9_" + i);
        }
    }

    @Test(priority = 50, dependsOnGroups = "set")
    public void getReflectionByName() {
        for (int i = 0; i < max; i++) {
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
