
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.time.LocalDate;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.structure.ChainMapImpl;
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
public class EmployeeAccessorSwitchDirect2 implements PropertyAccessor<Employee> {

    private static final EmployeeIdProperty idProperty = new EmployeeIdProperty();
    private static final EmployeeNameProperty nameProperty = new EmployeeNameProperty();
    private static final EmployeeBirthDateProperty birthDateProperty = new EmployeeBirthDateProperty();
    private static final EmployeeGenderProperty genderProperty = new EmployeeGenderProperty();
    private static final EmployeeDepartmentIdProperty departmentIdProperty = new EmployeeDepartmentIdProperty();
    private static final EmployeeOrganIdProperty organIdProperty = new EmployeeOrganIdProperty();
    private static final EmployeeIdentityCardProperty identityCardProperty = new EmployeeIdentityCardProperty();
    private static final EmployeeMobileProperty mobileProperty = new EmployeeMobileProperty();
    private static final EmployeeEmailProperty emailProperty = new EmployeeEmailProperty();
    private static final EmployeeFaxProperty faxProperty = new EmployeeFaxProperty();
    private static final EmployeeRemark0Property remark0Property = new EmployeeRemark0Property();
    private static final EmployeeRemark1Property remark1Property = new EmployeeRemark1Property();
    private static final EmployeeRemark2Property remark2Property = new EmployeeRemark2Property();
    private static final EmployeeRemark3Property remark3Property = new EmployeeRemark3Property();
    private static final EmployeeRemark4Property remark4Property = new EmployeeRemark4Property();
    private static final EmployeeRemark5Property remark5Property = new EmployeeRemark5Property();
    private static final EmployeeRemark6Property remark6Property = new EmployeeRemark6Property();
    private static final EmployeeRemark7Property remark7Property = new EmployeeRemark7Property();
    private static final EmployeeRemark8Property remark8Property = new EmployeeRemark8Property();
    private static final EmployeeRemark9Property remark9Property = new EmployeeRemark9Property();
    private static final EmployeeCreateUserProperty createUserProperty = new EmployeeCreateUserProperty(
        UserAccessorSwitchDirect.INSTANCE);

    @SuppressWarnings("rawtypes")
    private static final Map<String, Property> map = new ChainMapImpl<String, Property>().putChain("id", idProperty)
        .putChain("name", nameProperty).putChain("birthDate", birthDateProperty).putChain("gender", genderProperty)
        .putChain("departmentId", departmentIdProperty).putChain("organId", organIdProperty)
        .putChain("identityCard", identityCardProperty).putChain("mobile", mobileProperty)
        .putChain("email", emailProperty).putChain("fax", faxProperty).putChain("remark0", remark0Property)
        .putChain("remark1", remark1Property).putChain("remark2", remark2Property).putChain("remark3", remark3Property)
        .putChain("remark4", remark4Property).putChain("remark5", remark5Property).putChain("remark6", remark6Property)
        .putChain("remark7", remark7Property).putChain("remark8", remark8Property).putChain("remark9", remark9Property)
        .putChain("createUser", createUserProperty);

    public EmployeeAccessorSwitchDirect2() {

        //        map.put("id", idProperty);
        //        map.put("name", nameProperty);
        //        map.put("birthDate", birthDateProperty);
        //        map.put("gender", genderProperty);
        //        map.put("departmentId", departmentIdProperty);
        //        map.put("organId", organIdProperty);
        //        map.put("identityCard", identityCardProperty);
        //        map.put("mobile", mobileProperty);
        //        map.put("email", emailProperty);
        //        map.put("fax", faxProperty);
        //        map.put("remark0", remark0Property);
        //        map.put("remark1", remark1Property);
        //        map.put("remark2", remark2Property);
        //        map.put("remark3", remark3Property);
        //        map.put("remark4", remark4Property);
        //        map.put("remark5", remark5Property);
        //        map.put("remark6", remark6Property);
        //        map.put("remark7", remark7Property);
        //        map.put("remark8", remark8Property);
        //        map.put("remark9", remark9Property);
        //        map.put("createUser", createUserProperty);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Employee obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Employee obj, String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return getPropertyValue(obj, names[0]);
        }
        Property<Employee, Object> property = getProperty(names[0]);
        Object value = property.get(obj);
        if (value == null) {
            return null;
        }
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(Strings.format("bean {} property {} type {} can not be visit property",
                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
        }
        return visitor.getPropertyValue(value, ArrayUtils.subarray(names, 1, names.length));
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
    public int[] getPropertyIndexes(String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return new int[] { getProperty(names[0]).getIndex() };
        }
        Property<Employee, Object> property = getProperty(names[0]);
        PropertyAccessor<Object> visitor = property.getPropertyAccessor();
        if (visitor == null) {
            throw new IllegalArgumentException(
                Strings.format("bean {} property {} type {} can not be visit property", "后续来加入名称", "后续来加入类型"));
        }
        return ArrayUtils.addAll(new int[] { property.getIndex() },
            visitor.getPropertyIndexes(ArrayUtils.subarray(names, 1, names.length)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPropertyIndex(String name) {
        return getProperty(name).getIndex();
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
    @Override
    public void setPropertyValue(Employee obj, int[] indexes, Object value) {
        // YUFEI_TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Employee bean, String[] names, Object value) {
        // YUFEI_TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Property<Employee, ?>[] getProperties() {
        return map.values().toArray(new Property[map.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, V> Property<T1, V> getProperty(int... indexes) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1, V> Property<T1, V> getProperty(String... names) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @SuppressWarnings("unchecked")
    //    @Override
    //    protected <V> PropertyVisitor<V> getPropertyVisitor(int index) {
    //        switch (index) {
    //            case 20:
    //                return (PropertyVisitor<V>) UserVisitor2.INSTANCE;
    //            default:
    //                throw new IllegalArgumentException("no property at index " + index);
    //        }
    //    }
}
