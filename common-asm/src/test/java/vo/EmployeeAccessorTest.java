
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

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class EmployeeAccessorTest extends TestBase {

    EmployeeAccessor ev = new EmployeeAccessor();
    EmployeeAccessorSwitch evSwitch = new EmployeeAccessorSwitch();
    EmployeeAccessorSwitch2 evSwitch2 = new EmployeeAccessorSwitch2();
    EmployeeAccessorSwitchDirect evSwitchDirect = EmployeeAccessorSwitchDirect.INSTANCE;
    EmployeeAccessorSwitchDirect2 evSwitchDirect2 = new EmployeeAccessorSwitchDirect2();
    EmployeeAccessorSwitchDirect3 evSwitchDirect3 = new EmployeeAccessorSwitchDirect3();
    PropertyAccessor<Employee> pae;

    Employee e = new Employee();
    LocalDate date = LocalDate.now();

    BeanDescriptor<Employee> bd = BeanDescriptor.getBeanDescriptor(Employee.class);

    @BeforeClass
    void b() {
        pae = new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()).create(Employee.class);
    }

    @AfterMethod
    void am() {
        System.out.println("id: " + e.getId());
        System.out.println("name: " + e.getName());
        System.out.println("gender: " + e.getGender());
    }

    @Test(groups = "setEmployee")
    public void set() {
        for (int i = 0; i < total; i++) {
            e.setId(Long.valueOf(i));
            e.setName("name_");
            e.setBirthDate(date);
            e.setGender("MALE");
            e.setDepartmentId(i);
            e.setOrganId(i);
            e.setIdentityCard("idCard_");
            e.setMobile("mobile_");
            e.setEmail("email_");
            e.setFax("fax_");
            e.setRemark0("remark0_");
            e.setRemark1("remark1_");
            e.setRemark2("remark2_");
            e.setRemark3("remark3_");
            e.setRemark4("remark4_");
            e.setRemark5("remark5_");
            e.setRemark6("remark6_");
            e.setRemark7("remark7_");
            e.setRemark8("remark8_");
            e.setRemark9("remark9_");
        }
    }

    @Test(priority = 0, dependsOnGroups = "setEmployee")
    public void get() {
        for (int i = 0; i < total; i++) {
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

    @Test(groups = "setEmployee")
    public void setAccessor() {
        for (int i = 0; i < total; i++) {
            ev.setPropertyValue(e, 0, Long.valueOf(i));
            ev.setPropertyValue(e, 1, "name_");
            ev.setPropertyValue(e, 2, date);
            ev.setPropertyValue(e, 3, "MALE");
            ev.setPropertyValue(e, 4, i);
            ev.setPropertyValue(e, 5, i);
            ev.setPropertyValue(e, 6, "idCard_");
            ev.setPropertyValue(e, 7, "mobile_");
            ev.setPropertyValue(e, 8, "email_");
            ev.setPropertyValue(e, 9, "fax_");
            ev.setPropertyValue(e, 10, "remark0_");
            ev.setPropertyValue(e, 11, "remark1_");
            ev.setPropertyValue(e, 12, "remark2_");
            ev.setPropertyValue(e, 13, "remark3_");
            ev.setPropertyValue(e, 14, "remark4_");
            ev.setPropertyValue(e, 15, "remark5_");
            ev.setPropertyValue(e, 16, "remark6_");
            ev.setPropertyValue(e, 17, "remark7_");
            ev.setPropertyValue(e, 18, "remark8_");
            ev.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 10, dependsOnGroups = "setEmployee")
    public void getAccessor() {
        for (int i = 0; i < total; i++) {
            ev.getPropertyValue(e, 0);
            ev.getPropertyValue(e, 1);
            ev.getPropertyValue(e, 2);
            ev.getPropertyValue(e, 3);
            ev.getPropertyValue(e, 4);
            ev.getPropertyValue(e, 5);
            ev.getPropertyValue(e, 6);
            ev.getPropertyValue(e, 7);
            ev.getPropertyValue(e, 8);
            ev.getPropertyValue(e, 9);
            ev.getPropertyValue(e, 10);
            ev.getPropertyValue(e, 11);
            ev.getPropertyValue(e, 12);
            ev.getPropertyValue(e, 13);
            ev.getPropertyValue(e, 14);
            ev.getPropertyValue(e, 15);
            ev.getPropertyValue(e, 16);
            ev.getPropertyValue(e, 17);
            ev.getPropertyValue(e, 18);
            ev.getPropertyValue(e, 19);
        }
    }

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessor")
    public void setAccessorByName() {
        for (int i = 0; i < total; i++) {
            ev.setPropertyValue(e, "id", Long.valueOf(i));
            ev.setPropertyValue(e, "name", "name_");
            ev.setPropertyValue(e, "birthDate", date);
            ev.setPropertyValue(e, "gender", "MALE");
            ev.setPropertyValue(e, "departmentId", i);
            ev.setPropertyValue(e, "organId", i);
            ev.setPropertyValue(e, "identityCard", "idCard_");
            ev.setPropertyValue(e, "mobile", "mobile_");
            ev.setPropertyValue(e, "email", "email_");
            ev.setPropertyValue(e, "fax", "fax_");
            ev.setPropertyValue(e, "remark0", "remark0_");
            ev.setPropertyValue(e, "remark1", "remark1_");
            ev.setPropertyValue(e, "remark2", "remark2_");
            ev.setPropertyValue(e, "remark3", "remark3_");
            ev.setPropertyValue(e, "remark4", "remark4_");
            ev.setPropertyValue(e, "remark5", "remark5_");
            ev.setPropertyValue(e, "remark6", "remark6_");
            ev.setPropertyValue(e, "remark7", "remark7_");
            ev.setPropertyValue(e, "remark8", "remark8_");
            ev.setPropertyValue(e, "remark9", "remark9_");
        }
    }

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorByName")
    public void setAccessorByNameWithSwitch() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect3.setPropertyValue(e, "id", Long.valueOf(i));
            evSwitchDirect3.setPropertyValue(e, "name", "name_");
            evSwitchDirect3.setPropertyValue(e, "birthDate", date);
            evSwitchDirect3.setPropertyValue(e, "gender", "MALE");
            evSwitchDirect3.setPropertyValue(e, "departmentId", i);
            evSwitchDirect3.setPropertyValue(e, "organId", i);
            evSwitchDirect3.setPropertyValue(e, "identityCard", "idCard_");
            evSwitchDirect3.setPropertyValue(e, "mobile", "mobile_");
            evSwitchDirect3.setPropertyValue(e, "email", "email_");
            evSwitchDirect3.setPropertyValue(e, "fax", "fax_");
            evSwitchDirect3.setPropertyValue(e, "remark0", "remark0_");
            evSwitchDirect3.setPropertyValue(e, "remark1", "remark1_");
            evSwitchDirect3.setPropertyValue(e, "remark2", "remark2_");
            evSwitchDirect3.setPropertyValue(e, "remark3", "remark3_");
            evSwitchDirect3.setPropertyValue(e, "remark4", "remark4_");
            evSwitchDirect3.setPropertyValue(e, "remark5", "remark5_");
            evSwitchDirect3.setPropertyValue(e, "remark6", "remark6_");
            evSwitchDirect3.setPropertyValue(e, "remark7", "remark7_");
            evSwitchDirect3.setPropertyValue(e, "remark8", "remark8_");
            evSwitchDirect3.setPropertyValue(e, "remark9", "remark9_");
        }
    }

    @Test(priority = 20, dependsOnGroups = "setEmployee")
    public void getAccessorByName() {
        for (int i = 0; i < total; i++) {
            ev.getPropertyValue(e, "id");
            ev.getPropertyValue(e, "name");
            ev.getPropertyValue(e, "birthDate");
            ev.getPropertyValue(e, "gender");
            ev.getPropertyValue(e, "departmentId");
            ev.getPropertyValue(e, "organId");
            ev.getPropertyValue(e, "identityCard");
            ev.getPropertyValue(e, "mobile");
            ev.getPropertyValue(e, "email");
            ev.getPropertyValue(e, "fax");
            ev.getPropertyValue(e, "remark0");
            ev.getPropertyValue(e, "remark1");
            ev.getPropertyValue(e, "remark2");
            ev.getPropertyValue(e, "remark3");
            ev.getPropertyValue(e, "remark4");
            ev.getPropertyValue(e, "remark5");
            ev.getPropertyValue(e, "remark6");
            ev.getPropertyValue(e, "remark7");
            ev.getPropertyValue(e, "remark8");
            ev.getPropertyValue(e, "remark9");
        }
    }

    @Test(priority = 20, dependsOnGroups = "setEmployee")
    public void getAccessorByNameWithSwitch() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect3.getPropertyValue(e, "id");
            evSwitchDirect3.getPropertyValue(e, "name");
            evSwitchDirect3.getPropertyValue(e, "birthDate");
            evSwitchDirect3.getPropertyValue(e, "gender");
            evSwitchDirect3.getPropertyValue(e, "departmentId");
            evSwitchDirect3.getPropertyValue(e, "organId");
            evSwitchDirect3.getPropertyValue(e, "identityCard");
            evSwitchDirect3.getPropertyValue(e, "mobile");
            evSwitchDirect3.getPropertyValue(e, "email");
            evSwitchDirect3.getPropertyValue(e, "fax");
            evSwitchDirect3.getPropertyValue(e, "remark0");
            evSwitchDirect3.getPropertyValue(e, "remark1");
            evSwitchDirect3.getPropertyValue(e, "remark2");
            evSwitchDirect3.getPropertyValue(e, "remark3");
            evSwitchDirect3.getPropertyValue(e, "remark4");
            evSwitchDirect3.getPropertyValue(e, "remark5");
            evSwitchDirect3.getPropertyValue(e, "remark6");
            evSwitchDirect3.getPropertyValue(e, "remark7");
            evSwitchDirect3.getPropertyValue(e, "remark8");
            evSwitchDirect3.getPropertyValue(e, "remark9");
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessor")
    public void setAccessorSwitch() {
        for (int i = 0; i < total; i++) {
            evSwitch.setPropertyValue(e, 0, Long.valueOf(i));
            evSwitch.setPropertyValue(e, 1, "name_");
            evSwitch.setPropertyValue(e, 2, date);
            evSwitch.setPropertyValue(e, 3, "MALE");
            evSwitch.setPropertyValue(e, 4, i);
            evSwitch.setPropertyValue(e, 5, i);
            evSwitch.setPropertyValue(e, 6, "idCard_");
            evSwitch.setPropertyValue(e, 7, "mobile_");
            evSwitch.setPropertyValue(e, 8, "email_");
            evSwitch.setPropertyValue(e, 9, "fax_");
            evSwitch.setPropertyValue(e, 10, "remark0_");
            evSwitch.setPropertyValue(e, 11, "remark1_");
            evSwitch.setPropertyValue(e, 12, "remark2_");
            evSwitch.setPropertyValue(e, 13, "remark3_");
            evSwitch.setPropertyValue(e, 14, "remark4_");
            evSwitch.setPropertyValue(e, 15, "remark5_");
            evSwitch.setPropertyValue(e, 16, "remark6_");
            evSwitch.setPropertyValue(e, 17, "remark7_");
            evSwitch.setPropertyValue(e, 18, "remark8_");
            evSwitch.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 15, dependsOnGroups = "setEmployee")
    public void getAccessorSwitch() {
        for (int i = 0; i < total; i++) {
            evSwitch.getPropertyValue(e, 0);
            evSwitch.getPropertyValue(e, 1);
            evSwitch.getPropertyValue(e, 2);
            evSwitch.getPropertyValue(e, 3);
            evSwitch.getPropertyValue(e, 4);
            evSwitch.getPropertyValue(e, 5);
            evSwitch.getPropertyValue(e, 6);
            evSwitch.getPropertyValue(e, 7);
            evSwitch.getPropertyValue(e, 8);
            evSwitch.getPropertyValue(e, 9);
            evSwitch.getPropertyValue(e, 10);
            evSwitch.getPropertyValue(e, 11);
            evSwitch.getPropertyValue(e, 12);
            evSwitch.getPropertyValue(e, 13);
            evSwitch.getPropertyValue(e, 14);
            evSwitch.getPropertyValue(e, 15);
            evSwitch.getPropertyValue(e, 16);
            evSwitch.getPropertyValue(e, 17);
            evSwitch.getPropertyValue(e, 18);
            evSwitch.getPropertyValue(e, 19);
        }
    }

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorSwitch")
    public void setAccessorSwitchByName() {
        for (int i = 0; i < total; i++) {
            evSwitch.setPropertyValue(e, "id", Long.valueOf(i));
            evSwitch.setPropertyValue(e, "name", "name_");
            evSwitch.setPropertyValue(e, "birthDate", date);
            evSwitch.setPropertyValue(e, "gender", "MALE");
            evSwitch.setPropertyValue(e, "departmentId", i);
            evSwitch.setPropertyValue(e, "organId", i);
            evSwitch.setPropertyValue(e, "identityCard", "idCard_");
            evSwitch.setPropertyValue(e, "mobile", "mobile_");
            evSwitch.setPropertyValue(e, "email", "email_");
            evSwitch.setPropertyValue(e, "fax", "fax_");
            evSwitch.setPropertyValue(e, "remark0", "remark0_");
            evSwitch.setPropertyValue(e, "remark1", "remark1_");
            evSwitch.setPropertyValue(e, "remark2", "remark2_");
            evSwitch.setPropertyValue(e, "remark3", "remark3_");
            evSwitch.setPropertyValue(e, "remark4", "remark4_");
            evSwitch.setPropertyValue(e, "remark5", "remark5_");
            evSwitch.setPropertyValue(e, "remark6", "remark6_");
            evSwitch.setPropertyValue(e, "remark7", "remark7_");
            evSwitch.setPropertyValue(e, "remark8", "remark8_");
            evSwitch.setPropertyValue(e, "remark9", "remark9_");
        }
    }

    @Test(priority = 20, dependsOnGroups = "setEmployee")
    public void getAccessorSwitchByName() {
        for (int i = 0; i < total; i++) {
            evSwitch.getPropertyValue(e, "id");
            evSwitch.getPropertyValue(e, "name");
            evSwitch.getPropertyValue(e, "birthDate");
            evSwitch.getPropertyValue(e, "gender");
            evSwitch.getPropertyValue(e, "departmentId");
            evSwitch.getPropertyValue(e, "organId");
            evSwitch.getPropertyValue(e, "identityCard");
            evSwitch.getPropertyValue(e, "mobile");
            evSwitch.getPropertyValue(e, "email");
            evSwitch.getPropertyValue(e, "fax");
            evSwitch.getPropertyValue(e, "remark0");
            evSwitch.getPropertyValue(e, "remark1");
            evSwitch.getPropertyValue(e, "remark2");
            evSwitch.getPropertyValue(e, "remark3");
            evSwitch.getPropertyValue(e, "remark4");
            evSwitch.getPropertyValue(e, "remark5");
            evSwitch.getPropertyValue(e, "remark6");
            evSwitch.getPropertyValue(e, "remark7");
            evSwitch.getPropertyValue(e, "remark8");
            evSwitch.getPropertyValue(e, "remark9");
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorSwitch")
    public void setAccessorSwitch2() {
        for (int i = 0; i < total; i++) {
            evSwitch2.setPropertyValue(e, 0, Long.valueOf(i));
            evSwitch2.setPropertyValue(e, 1, "name_");
            evSwitch2.setPropertyValue(e, 2, date);
            evSwitch2.setPropertyValue(e, 3, "MALE");
            evSwitch2.setPropertyValue(e, 4, i);
            evSwitch2.setPropertyValue(e, 5, i);
            evSwitch2.setPropertyValue(e, 6, "idCard_");
            evSwitch2.setPropertyValue(e, 7, "mobile_");
            evSwitch2.setPropertyValue(e, 8, "email_");
            evSwitch2.setPropertyValue(e, 9, "fax_");
            evSwitch2.setPropertyValue(e, 10, "remark0_");
            evSwitch2.setPropertyValue(e, 11, "remark1_");
            evSwitch2.setPropertyValue(e, 12, "remark2_");
            evSwitch2.setPropertyValue(e, 13, "remark3_");
            evSwitch2.setPropertyValue(e, 14, "remark4_");
            evSwitch2.setPropertyValue(e, 15, "remark5_");
            evSwitch2.setPropertyValue(e, 16, "remark6_");
            evSwitch2.setPropertyValue(e, 17, "remark7_");
            evSwitch2.setPropertyValue(e, 18, "remark8_");
            evSwitch2.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 15, dependsOnGroups = "setEmployee")
    public void getAccessorSwitch2() {
        for (int i = 0; i < total; i++) {
            evSwitch2.getPropertyValue(e, 0);
            evSwitch2.getPropertyValue(e, 1);
            evSwitch2.getPropertyValue(e, 2);
            evSwitch2.getPropertyValue(e, 3);
            evSwitch2.getPropertyValue(e, 4);
            evSwitch2.getPropertyValue(e, 5);
            evSwitch2.getPropertyValue(e, 6);
            evSwitch2.getPropertyValue(e, 7);
            evSwitch2.getPropertyValue(e, 8);
            evSwitch2.getPropertyValue(e, 9);
            evSwitch2.getPropertyValue(e, 10);
            evSwitch2.getPropertyValue(e, 11);
            evSwitch2.getPropertyValue(e, 12);
            evSwitch2.getPropertyValue(e, 13);
            evSwitch2.getPropertyValue(e, 14);
            evSwitch2.getPropertyValue(e, 15);
            evSwitch2.getPropertyValue(e, 16);
            evSwitch2.getPropertyValue(e, 17);
            evSwitch2.getPropertyValue(e, 18);
            evSwitch2.getPropertyValue(e, 19);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorSwitch2")
    public void setAccessorSwitchDirect() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect.setPropertyValue(e, 0, Long.valueOf(i));
            evSwitchDirect.setPropertyValue(e, 1, "name_");
            evSwitchDirect.setPropertyValue(e, 2, date);
            evSwitchDirect.setPropertyValue(e, 3, "MALE");
            evSwitchDirect.setPropertyValue(e, 4, i);
            evSwitchDirect.setPropertyValue(e, 5, i);
            evSwitchDirect.setPropertyValue(e, 6, "idCard_");
            evSwitchDirect.setPropertyValue(e, 7, "mobile_");
            evSwitchDirect.setPropertyValue(e, 8, "email_");
            evSwitchDirect.setPropertyValue(e, 9, "fax_");
            evSwitchDirect.setPropertyValue(e, 10, "remark0_");
            evSwitchDirect.setPropertyValue(e, 11, "remark1_");
            evSwitchDirect.setPropertyValue(e, 12, "remark2_");
            evSwitchDirect.setPropertyValue(e, 13, "remark3_");
            evSwitchDirect.setPropertyValue(e, 14, "remark4_");
            evSwitchDirect.setPropertyValue(e, 15, "remark5_");
            evSwitchDirect.setPropertyValue(e, 16, "remark6_");
            evSwitchDirect.setPropertyValue(e, 17, "remark7_");
            evSwitchDirect.setPropertyValue(e, 18, "remark8_");
            evSwitchDirect.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 15, dependsOnGroups = "setEmployee")
    public void getAccessorSwitchDirect() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect.getPropertyValue(e, 0);
            evSwitchDirect.getPropertyValue(e, 1);
            evSwitchDirect.getPropertyValue(e, 2);
            evSwitchDirect.getPropertyValue(e, 3);
            evSwitchDirect.getPropertyValue(e, 4);
            evSwitchDirect.getPropertyValue(e, 5);
            evSwitchDirect.getPropertyValue(e, 6);
            evSwitchDirect.getPropertyValue(e, 7);
            evSwitchDirect.getPropertyValue(e, 8);
            evSwitchDirect.getPropertyValue(e, 9);
            evSwitchDirect.getPropertyValue(e, 10);
            evSwitchDirect.getPropertyValue(e, 11);
            evSwitchDirect.getPropertyValue(e, 12);
            evSwitchDirect.getPropertyValue(e, 13);
            evSwitchDirect.getPropertyValue(e, 14);
            evSwitchDirect.getPropertyValue(e, 15);
            evSwitchDirect.getPropertyValue(e, 16);
            evSwitchDirect.getPropertyValue(e, 17);
            evSwitchDirect.getPropertyValue(e, 18);
            evSwitchDirect.getPropertyValue(e, 19);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorSwitchDirect")
    public void setAccessorSwitchDirect2() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect2.setPropertyValue(e, 0, Long.valueOf(i));
            evSwitchDirect2.setPropertyValue(e, 1, "name_");
            evSwitchDirect2.setPropertyValue(e, 2, date);
            evSwitchDirect2.setPropertyValue(e, 3, "MALE");
            evSwitchDirect2.setPropertyValue(e, 4, i);
            evSwitchDirect2.setPropertyValue(e, 5, i);
            evSwitchDirect2.setPropertyValue(e, 6, "idCard_");
            evSwitchDirect2.setPropertyValue(e, 7, "mobile_");
            evSwitchDirect2.setPropertyValue(e, 8, "email_");
            evSwitchDirect2.setPropertyValue(e, 9, "fax_");
            evSwitchDirect2.setPropertyValue(e, 10, "remark0_");
            evSwitchDirect2.setPropertyValue(e, 11, "remark1_");
            evSwitchDirect2.setPropertyValue(e, 12, "remark2_");
            evSwitchDirect2.setPropertyValue(e, 13, "remark3_");
            evSwitchDirect2.setPropertyValue(e, 14, "remark4_");
            evSwitchDirect2.setPropertyValue(e, 15, "remark5_");
            evSwitchDirect2.setPropertyValue(e, 16, "remark6_");
            evSwitchDirect2.setPropertyValue(e, 17, "remark7_");
            evSwitchDirect2.setPropertyValue(e, 18, "remark8_");
            evSwitchDirect2.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 25, dependsOnGroups = "setEmployee")
    public void getAccessorSwitchDirect2() {
        for (int i = 0; i < total; i++) {
            evSwitchDirect2.getPropertyValue(e, 0);
            evSwitchDirect2.getPropertyValue(e, 1);
            evSwitchDirect2.getPropertyValue(e, 2);
            evSwitchDirect2.getPropertyValue(e, 3);
            evSwitchDirect2.getPropertyValue(e, 4);
            evSwitchDirect2.getPropertyValue(e, 5);
            evSwitchDirect2.getPropertyValue(e, 6);
            evSwitchDirect2.getPropertyValue(e, 7);
            evSwitchDirect2.getPropertyValue(e, 8);
            evSwitchDirect2.getPropertyValue(e, 9);
            evSwitchDirect2.getPropertyValue(e, 10);
            evSwitchDirect2.getPropertyValue(e, 11);
            evSwitchDirect2.getPropertyValue(e, 12);
            evSwitchDirect2.getPropertyValue(e, 13);
            evSwitchDirect2.getPropertyValue(e, 14);
            evSwitchDirect2.getPropertyValue(e, 15);
            evSwitchDirect2.getPropertyValue(e, 16);
            evSwitchDirect2.getPropertyValue(e, 17);
            evSwitchDirect2.getPropertyValue(e, 18);
            evSwitchDirect2.getPropertyValue(e, 19);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test(groups = "setEmployee", dependsOnMethods = "setAccessorSwitchDirect2")
    public void setAccessorByAsmPropertyAccessorFactory() {
        for (int i = 0; i < total; i++) {
            pae.setPropertyValue(e, 0, Long.valueOf(i));
            pae.setPropertyValue(e, 1, "name_");
            pae.setPropertyValue(e, 2, date);
            pae.setPropertyValue(e, 3, "MALE");
            pae.setPropertyValue(e, 4, i);
            pae.setPropertyValue(e, 5, i);
            pae.setPropertyValue(e, 6, "idCard_");
            pae.setPropertyValue(e, 7, "mobile_");
            pae.setPropertyValue(e, 8, "email_");
            pae.setPropertyValue(e, 9, "fax_");
            pae.setPropertyValue(e, 10, "remark0_");
            pae.setPropertyValue(e, 11, "remark1_");
            pae.setPropertyValue(e, 12, "remark2_");
            pae.setPropertyValue(e, 13, "remark3_");
            pae.setPropertyValue(e, 14, "remark4_");
            pae.setPropertyValue(e, 15, "remark5_");
            pae.setPropertyValue(e, 16, "remark6_");
            pae.setPropertyValue(e, 17, "remark7_");
            pae.setPropertyValue(e, 18, "remark8_");
            pae.setPropertyValue(e, 19, "remark9_");
        }
    }

    @Test(priority = 35, dependsOnGroups = "setEmployee")
    public void getAccessorByAsmPropertyAccessorFactory() {
        for (int i = 0; i < total; i++) {
            pae.getPropertyValue(e, 0);
            pae.getPropertyValue(e, 1);
            pae.getPropertyValue(e, 2);
            pae.getPropertyValue(e, 3);
            pae.getPropertyValue(e, 4);
            pae.getPropertyValue(e, 5);
            pae.getPropertyValue(e, 6);
            pae.getPropertyValue(e, 7);
            pae.getPropertyValue(e, 8);
            pae.getPropertyValue(e, 9);
            pae.getPropertyValue(e, 10);
            pae.getPropertyValue(e, 11);
            pae.getPropertyValue(e, 12);
            pae.getPropertyValue(e, 13);
            pae.getPropertyValue(e, 14);
            pae.getPropertyValue(e, 15);
            pae.getPropertyValue(e, 16);
            pae.getPropertyValue(e, 17);
            pae.getPropertyValue(e, 18);
            pae.getPropertyValue(e, 19);
        }
    }
}
