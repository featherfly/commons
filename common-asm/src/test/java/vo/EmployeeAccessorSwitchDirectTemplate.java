
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.exception.UnsupportedException;
import vo.AbstractEmployeeAccessor.EmployeeBirthDateProperty;
import vo.AbstractEmployeeAccessor.EmployeeCreateUserProperty;
import vo.AbstractEmployeeAccessor.EmployeeDepartmentIdProperty;
import vo.AbstractEmployeeAccessor.EmployeeEmailProperty;
import vo.AbstractEmployeeAccessor.EmployeeFaxProperty;
import vo.AbstractEmployeeAccessor.EmployeeGenderProperty;
import vo.AbstractEmployeeAccessor.EmployeeIdProperty;
import vo.AbstractEmployeeAccessor.EmployeeIdentityCardProperty;
import vo.AbstractEmployeeAccessor.EmployeeMobileProperty;
import vo.AbstractEmployeeAccessor.EmployeeNameProperty;
import vo.AbstractEmployeeAccessor.EmployeeOrganIdProperty;
import vo.AbstractEmployeeAccessor.EmployeeRemark0Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark1Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark2Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark3Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark4Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark5Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark6Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark7Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark8Property;
import vo.AbstractEmployeeAccessor.EmployeeRemark9Property;

/**
 * EmployeeProperties.
 *
 * @author zhongj
 */
public class EmployeeAccessorSwitchDirectTemplate extends AbstractPropertyAccessor<Employee> {

    public static final EmployeeAccessorSwitchDirectTemplate INSTANCE = new EmployeeAccessorSwitchDirectTemplate();

    @SuppressWarnings("rawtypes")
    private final Map<String, Property> map = new HashMap<>();

    private final EmployeeIdProperty idProperty = new EmployeeIdProperty();
    private final EmployeeNameProperty nameProperty = new EmployeeNameProperty();
    private final EmployeeBirthDateProperty birthDateProperty = new EmployeeBirthDateProperty();
    private final EmployeeGenderProperty genderProperty = new EmployeeGenderProperty();
    private final EmployeeDepartmentIdProperty departmentIdProperty = new EmployeeDepartmentIdProperty();
    private final EmployeeOrganIdProperty organIdProperty = new EmployeeOrganIdProperty();
    private final EmployeeIdentityCardProperty identityCardProperty = new EmployeeIdentityCardProperty();
    private final EmployeeMobileProperty mobileProperty = new EmployeeMobileProperty();
    private final EmployeeEmailProperty emailProperty = new EmployeeEmailProperty();
    private final EmployeeFaxProperty faxProperty = new EmployeeFaxProperty();
    private final EmployeeRemark0Property remark0Property = new EmployeeRemark0Property();
    private final EmployeeRemark1Property remark1Property = new EmployeeRemark1Property();
    private final EmployeeRemark2Property remark2Property = new EmployeeRemark2Property();
    private final EmployeeRemark3Property remark3Property = new EmployeeRemark3Property();
    private final EmployeeRemark4Property remark4Property = new EmployeeRemark4Property();
    private final EmployeeRemark5Property remark5Property = new EmployeeRemark5Property();
    private final EmployeeRemark6Property remark6Property = new EmployeeRemark6Property();
    private final EmployeeRemark7Property remark7Property = new EmployeeRemark7Property();
    private final EmployeeRemark8Property remark8Property = new EmployeeRemark8Property();
    private final EmployeeRemark9Property remark9Property = new EmployeeRemark9Property();
    private final EmployeeCreateUserProperty createUserProperty = new EmployeeCreateUserProperty(
        UserAccessorSwitchDirect.INSTANCE);

    private EmployeeAccessorSwitchDirectTemplate() {

        map.put("id", idProperty);
        map.put("name", nameProperty);
        map.put("birthDate", birthDateProperty);
        map.put("gender", genderProperty);
        map.put("departmentId", departmentIdProperty);
        map.put("organId", organIdProperty);
        map.put("identityCard", identityCardProperty);
        map.put("mobile", mobileProperty);
        map.put("email", emailProperty);
        map.put("fax", faxProperty);
        map.put("remark0", remark0Property);
        map.put("remark1", remark1Property);
        map.put("remark2", remark2Property);
        map.put("remark3", remark3Property);
        map.put("remark4", remark4Property);
        map.put("remark5", remark5Property);
        map.put("remark6", remark6Property);
        map.put("remark7", remark7Property);
        map.put("remark8", remark8Property);
        map.put("remark9", remark9Property);
        map.put("createUser", createUserProperty);
    }

    @Override
    public void setPropertyValue(Employee obj, int index, Object value) {
        switch (index) {
            case 0:
                obj.setId((Long) value);
                return;
            case 1:
                obj.setName((String) value);
                return;
            case 2:
                obj.setBirthDate((LocalDate) value);
                return;
            case 3:
                obj.setGender((String) value);
                return;
            case 4:
                obj.setDepartmentId((Integer) value);
                return;
            case 5:
                obj.setOrganId((Integer) value);
                return;
            case 6:
                obj.setIdentityCard((String) value);
                return;
            case 7:
                obj.setMobile((String) value);
                return;
            case 8:
                obj.setEmail((String) value);
                return;
            case 9:
                obj.setFax((String) value);
                return;
            case 10:
                obj.setRemark0((String) value);
                return;
            case 11:
                obj.setRemark1((String) value);
                return;
            case 12:
                obj.setRemark2((String) value);
                return;
            case 13:
                obj.setRemark3((String) value);
                return;
            case 14:
                obj.setRemark4((String) value);
                return;
            case 15:
                obj.setRemark5((String) value);
                return;
            case 16:
                obj.setRemark6((String) value);
                return;
            case 17:
                obj.setRemark7((String) value);
                return;
            case 18:
                obj.setRemark8((String) value);
                return;
            case 19:
                obj.setRemark9((String) value);
                return;
            case 20:
                obj.setCreateUser((User) value);
                return;
            default:
                throw new UnsupportedException();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPropertyValue(Employee obj, String name, Object value) {
        map.get(name).set(obj, value);
    }

    @Override
    public Object getPropertyValue(Employee obj, int index) {
        switch (index) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getName();
            case 2:
                return obj.getBirthDate();
            case 3:
                return obj.getGender();
            case 4:
                return obj.getDepartmentId();
            case 5:
                return obj.getOrganId();
            case 6:
                return obj.getIdentityCard();
            case 7:
                return obj.getMobile();
            case 8:
                return obj.getEmail();
            case 9:
                return obj.getFax();
            case 10:
                return obj.getRemark0();
            case 11:
                return obj.getRemark1();
            case 12:
                return obj.getRemark2();
            case 13:
                return obj.getRemark3();
            case 14:
                return obj.getRemark4();
            case 15:
                return obj.getRemark5();
            case 16:
                return obj.getRemark6();
            case 17:
                return obj.getRemark7();
            case 18:
                return obj.getRemark8();
            case 19:
                return obj.getRemark9();
            case 20:
                return obj.getCreateUser();
            default:
                throw new UnsupportedException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getPropertyValue(Employee obj, String name) {
        return map.get(name).get(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Employee, V> getProperty(int index) {
        switch (index) {
            case 0:
                return (Property<Employee, V>) idProperty;
            case 1:
                return (Property<Employee, V>) nameProperty;
            case 2:
                return (Property<Employee, V>) birthDateProperty;
            case 3:
                return (Property<Employee, V>) genderProperty;
            case 4:
                return (Property<Employee, V>) departmentIdProperty;
            case 5:
                return (Property<Employee, V>) organIdProperty;
            case 6:
                return (Property<Employee, V>) identityCardProperty;
            case 7:
                return (Property<Employee, V>) mobileProperty;
            case 8:
                return (Property<Employee, V>) emailProperty;
            case 9:
                return (Property<Employee, V>) faxProperty;
            case 10:
                return (Property<Employee, V>) remark0Property;
            case 11:
                return (Property<Employee, V>) remark1Property;
            case 12:
                return (Property<Employee, V>) remark2Property;
            case 13:
                return (Property<Employee, V>) remark3Property;
            case 14:
                return (Property<Employee, V>) remark4Property;
            case 15:
                return (Property<Employee, V>) remark5Property;
            case 16:
                return (Property<Employee, V>) remark6Property;
            case 17:
                return (Property<Employee, V>) remark7Property;
            case 18:
                return (Property<Employee, V>) remark8Property;
            case 19:
                return (Property<Employee, V>) remark9Property;
            case 20:
                return (Property<Employee, V>) createUserProperty;
            default:
                throw new UnsupportedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Employee, V> getProperty(String name) {
        return map.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee instantiate() {
        return new Employee();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Employee> getType() {
        return Employee.class;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Property<Employee, ?>[] getProperties() {
        return map.values().toArray(new Property[map.size()]);
    }
}
