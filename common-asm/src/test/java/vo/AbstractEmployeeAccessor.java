
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.time.LocalDate;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * EmployeeProperties.
 *
 * @author zhongj
 */
public abstract class AbstractEmployeeAccessor extends AbstractPropertyAccessor<Employee> {

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public Object getPropertyValue(Employee obj, String... names) {
    //        AssertIllegalArgument.isNotEmpty(names, "names");
    //        if (names.length == 1) {
    //            return getPropertyValue(obj, names[0]);
    //        }
    //        Property<Employee, Object> property = getProperty(names[0]);
    //        Object value = property.get(obj);
    //        if (value == null) {
    //            return null;
    //        }
    //        PropertyVisitor<Object> visitor = property.getPropertyVisitor();
    //        if (visitor == null) {
    //            throw new IllegalArgumentException(Strings.format("bean {} property {} type {} can not be visit property",
    //                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
    //        }
    //        return visitor.getPropertyValue(value, ArrayUtils.subarray(names, 1, names.length));
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public Object getPropertyValue(Employee obj, int... indexes) {
    //        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
    //        if (indexes.length == 1) {
    //            return getPropertyValue(obj, indexes[0]);
    //        }
    //        Property<Employee, Object> property = getProperty(indexes[0]);
    //        Object value = property.get(obj);
    //        if (value == null) {
    //            return null;
    //        }
    //        PropertyVisitor<Object> visitor = property.getPropertyVisitor();
    //        if (visitor == null) {
    //            throw new IllegalArgumentException(Strings.format("bean {} property {} type {} can not be visit property",
    //                obj.getClass().getName(), "后续来加入名称", "后续来加入类型"));
    //        }
    //        return visitor.getPropertyValue(value, ArrayUtils.subarray(indexes, 1, indexes.length));
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public int[] getIndex(String... names) {
    //        AssertIllegalArgument.isNotEmpty(names, "names");
    //        if (names.length == 1) {
    //            return new int[] { getProperty(names[0]).getIndex() };
    //        }
    //        Property<Employee, Object> property = getProperty(names[0]);
    //        PropertyVisitor<Object> visitor = property.getPropertyVisitor();
    //        if (visitor == null) {
    //            throw new IllegalArgumentException(
    //                Strings.format("bean {} property {} type {} can not be visit property", "后续来加入名称", "后续来加入类型"));
    //        }
    //        return ArrayUtils.addAll(new int[] { property.getIndex() },
    //            visitor.getIndex(ArrayUtils.subarray(names, 1, names.length)));
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public int getIndex(String name) {
    //        return getProperty(name).getIndex();
    //    }

    public static class EmployeeIdProperty extends AbstractProperty<Employee, Long> {

        /**
         * @param instanceType
         * @param propertyType
         * @param name
         * @param index
         * @param propertyVisitor
         */
        public EmployeeIdProperty() {
            super(Employee.class, Long.class, "id", 0, null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(Employee object, Long value) {
            object.setId(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Long get(Employee object) {
            return object.getId();
        }

    }

    public static class EmployeeNameProperty extends AbstractProperty<Employee, String> {
        public EmployeeNameProperty() {
            super(Employee.class, String.class, "name", 1, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setName(value);
        }

        @Override
        public String get(Employee object) {
            return object.getName();
        }
    }

    public static class EmployeeBirthDateProperty extends AbstractProperty<Employee, LocalDate> {
        public EmployeeBirthDateProperty() {
            super(Employee.class, LocalDate.class, "birthDate", 2, null);
        }

        @Override
        public void set(Employee object, LocalDate value) {
            object.setBirthDate(value);
        }

        @Override
        public LocalDate get(Employee object) {
            return object.getBirthDate();
        }
    }

    public static class EmployeeGenderProperty extends AbstractProperty<Employee, String> {
        public EmployeeGenderProperty() {
            super(Employee.class, String.class, "gender", 3, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setGender(value);
        }

        @Override
        public String get(Employee object) {
            return object.getGender();
        }
    }

    public static class EmployeeDepartmentIdProperty extends AbstractProperty<Employee, Integer> {
        public EmployeeDepartmentIdProperty() {
            super(Employee.class, Integer.class, "departmentId", 4, null);
        }

        @Override
        public void set(Employee object, Integer value) {
            object.setDepartmentId(value);
        }

        @Override
        public Integer get(Employee object) {
            return object.getDepartmentId();
        }
    }

    public static class EmployeeOrganIdProperty extends AbstractProperty<Employee, Integer> {
        public EmployeeOrganIdProperty() {
            super(Employee.class, Integer.class, "organId", 5, null);
        }

        @Override
        public void set(Employee object, Integer value) {
            object.setOrganId(value);
        }

        @Override
        public Integer get(Employee object) {
            return object.getOrganId();
        }
    }

    public static class EmployeeIdentityCardProperty extends AbstractProperty<Employee, String> {
        public EmployeeIdentityCardProperty() {
            super(Employee.class, String.class, "identityCard", 6, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setIdentityCard(value);
        }

        @Override
        public String get(Employee object) {
            return object.getIdentityCard();
        }
    }

    public static class EmployeeMobileProperty extends AbstractProperty<Employee, String> {
        public EmployeeMobileProperty() {
            super(Employee.class, String.class, "mobile", 7, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setMobile(value);
        }

        @Override
        public String get(Employee object) {
            return object.getMobile();
        }
    }

    public static class EmployeeEmailProperty extends AbstractProperty<Employee, String> {
        public EmployeeEmailProperty() {
            super(Employee.class, String.class, "email", 8, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setEmail(value);
        }

        @Override
        public String get(Employee object) {
            return object.getEmail();
        }
    }

    public static class EmployeeFaxProperty extends AbstractProperty<Employee, String> {
        public EmployeeFaxProperty() {
            super(Employee.class, String.class, "fax", 9, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setFax(value);
        }

        @Override
        public String get(Employee object) {
            return object.getFax();
        }
    }

    public static class EmployeeRemark0Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark0Property() {
            super(Employee.class, String.class, "remark0", 10, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark0(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark0();
        }
    }

    public static class EmployeeRemark1Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark1Property() {
            super(Employee.class, String.class, "remark1", 11, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark1(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark1();
        }
    }

    public static class EmployeeRemark2Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark2Property() {
            super(Employee.class, String.class, "remark2", 12, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark2(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark2();
        }
    }

    public static class EmployeeRemark3Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark3Property() {
            super(Employee.class, String.class, "remark3", 13, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark3(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark3();
        }
    }

    public static class EmployeeRemark4Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark4Property() {
            super(Employee.class, String.class, "remark4", 14, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark4(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark4();
        }
    }

    public static class EmployeeRemark5Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark5Property() {
            super(Employee.class, String.class, "remark5", 15, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark5(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark5();
        }
    }

    public static class EmployeeRemark6Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark6Property() {
            super(Employee.class, String.class, "remark6", 16, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark6(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark6();
        }
    }

    public static class EmployeeRemark7Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark7Property() {
            super(Employee.class, String.class, "remark7", 17, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark7(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark7();
        }
    }

    public static class EmployeeRemark8Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark8Property() {
            super(Employee.class, String.class, "remark8", 18, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark8(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark8();
        }
    }

    public static class EmployeeRemark9Property extends AbstractProperty<Employee, String> {
        public EmployeeRemark9Property() {
            super(Employee.class, String.class, "remark9", 19, null);
        }

        @Override
        public void set(Employee object, String value) {
            object.setRemark9(value);
        }

        @Override
        public String get(Employee object) {
            return object.getRemark9();
        }
    }

    public static class EmployeeCreateUserProperty extends AbstractProperty<Employee, User> {
        public EmployeeCreateUserProperty(PropertyAccessor<User> propertyVisitor) {
            super(Employee.class, User.class, "createUser", 20, propertyVisitor);
        }

        @Override
        public void set(Employee object, User value) {
            object.setCreateUser(value);
        }

        @Override
        public User get(Employee object) {
            return object.getCreateUser();
        }
    }

    @Override
    public Employee instantiate() {
        return new Employee();
    }

    @Override
    public Class<Employee> getType() {
        return Employee.class;
    }
}
