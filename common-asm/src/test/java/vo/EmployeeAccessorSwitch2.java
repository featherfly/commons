
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Strings;

/**
 * EmployeeProperties.
 *
 * @author zhongj
 */
public class EmployeeAccessorSwitch2 extends AbstractEmployeeAccessor {

    @SuppressWarnings("rawtypes")
    private Map<String, Property> map = new HashMap<>();

    private EmployeeIdProperty idProperty = new EmployeeIdProperty();
    private EmployeeNameProperty nameProperty = new EmployeeNameProperty();
    private EmployeeBirthDateProperty birthDateProperty = new EmployeeBirthDateProperty();
    private EmployeeGenderProperty genderProperty = new EmployeeGenderProperty();
    private EmployeeDepartmentIdProperty departmentIdProperty = new EmployeeDepartmentIdProperty();
    private EmployeeOrganIdProperty organIdProperty = new EmployeeOrganIdProperty();
    private EmployeeIdentityCardProperty identityCardProperty = new EmployeeIdentityCardProperty();
    private EmployeeMobileProperty mobileProperty = new EmployeeMobileProperty();
    private EmployeeEmailProperty emailProperty = new EmployeeEmailProperty();
    private EmployeeFaxProperty faxProperty = new EmployeeFaxProperty();
    private EmployeeRemark0Property remark0Property = new EmployeeRemark0Property();
    private EmployeeRemark1Property remark1Property = new EmployeeRemark1Property();
    private EmployeeRemark2Property remark2Property = new EmployeeRemark2Property();
    private EmployeeRemark3Property remark3Property = new EmployeeRemark3Property();
    private EmployeeRemark4Property remark4Property = new EmployeeRemark4Property();
    private EmployeeRemark5Property remark5Property = new EmployeeRemark5Property();
    private EmployeeRemark6Property remark6Property = new EmployeeRemark6Property();
    private EmployeeRemark7Property remark7Property = new EmployeeRemark7Property();
    private EmployeeRemark8Property remark8Property = new EmployeeRemark8Property();
    private EmployeeRemark9Property remark9Property = new EmployeeRemark9Property();
    private EmployeeCreateUserProperty createUserProperty = new EmployeeCreateUserProperty(
        UserAccessorSwitchDirect.INSTANCE);

    public EmployeeAccessorSwitch2() {

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
        getProperty(index).set(obj, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPropertyValue(Employee obj, String name, Object value) {
        map.get(name).set(obj, value);
    }

    @Override
    public Object getPropertyValue(Employee obj, int index) {
        return getProperty(index).get(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Employee obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
        //        Object value = getPropertyValue(obj, indexes[0]);
        Property<Employee, Object> property = getProperty(indexes[0]);
        Object value = property.get(obj);
        if (value == null) {
            return null;
        }
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(Strings.format("bean {} property {} type {} can not be visit property",
                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
        }
        return visitor.getPropertyValue(value, ArrayUtils.subarray(indexes, 1, indexes.length));
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
    @SuppressWarnings("unchecked")
    @Override
    public Property<Employee, ?>[] getProperties() {
        return map.values().toArray(new Property[map.size()]);
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @SuppressWarnings("unchecked")
    //    @Override
    //    protected <V> PropertyVisitor<V> getPropertyVisitor(int index) {
    //        return (PropertyVisitor<V>) getProperty(index).getPropertyVisitor();
    //    }

}
