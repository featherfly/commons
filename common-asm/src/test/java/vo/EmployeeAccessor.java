
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

import org.apache.commons.lang3.ArrayUtils;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Strings;

/**
 * EmployeeProperties.
 *
 * @author zhongj
 */
public class EmployeeAccessor implements PropertyAccessor<Employee> {

    @SuppressWarnings("rawtypes")
    private Property[] properties;

    @SuppressWarnings("rawtypes")
    private Map<String, Property> map = new HashMap<>();

    public EmployeeAccessor() {
        properties = new Property[21];

        properties[0] = new AbstractProperty<Employee, Long>(Employee.class, Long.class, "id", 0, null) {
            @Override
            public void set(Employee object, Long value) {
                object.setId(value);
            }

            @Override
            public Long get(Employee object) {
                return object.getId();
            }
        };

        properties[1] = new AbstractProperty<Employee, String>(Employee.class, String.class, "name", 1, null) {
            @Override
            public void set(Employee object, String value) {
                object.setName(value);
            }

            @Override
            public String get(Employee object) {
                return object.getName();
            }
        };

        properties[2] = new AbstractProperty<Employee, LocalDate>(Employee.class, LocalDate.class, "birthDate", 2,
            null) {
            @Override
            public void set(Employee object, LocalDate value) {
                object.setBirthDate(value);
            }

            @Override
            public LocalDate get(Employee object) {
                return object.getBirthDate();
            }
        };

        properties[3] = new AbstractProperty<Employee, String>(Employee.class, String.class, "gender", 3, null) {
            @Override
            public void set(Employee object, String value) {
                object.setGender(value);
            }

            @Override
            public String get(Employee object) {
                return object.getGender();
            }
        };

        properties[4] = new AbstractProperty<Employee, Integer>(Employee.class, Integer.class, "departmentId", 4,
            null) {
            @Override
            public void set(Employee object, Integer value) {
                object.setDepartmentId(value);
            }

            @Override
            public Integer get(Employee object) {
                return object.getDepartmentId();
            }
        };

        properties[5] = new AbstractProperty<Employee, Integer>(Employee.class, Integer.class, "organId", 5, null) {
            @Override
            public void set(Employee object, Integer value) {
                object.setOrganId(value);
            }

            @Override
            public Integer get(Employee object) {
                return object.getOrganId();
            }
        };
        properties[6] = new AbstractProperty<Employee, String>(Employee.class, String.class, "identityCard", 6, null) {
            @Override
            public void set(Employee object, String value) {
                object.setIdentityCard(value);
            }

            @Override
            public String get(Employee object) {
                return object.getIdentityCard();
            }
        };
        properties[7] = new AbstractProperty<Employee, String>(Employee.class, String.class, "mobile", 7, null) {
            @Override
            public void set(Employee object, String value) {
                object.setMobile(value);
            }

            @Override
            public String get(Employee object) {
                return object.getMobile();
            }
        };
        properties[8] = new AbstractProperty<Employee, String>(Employee.class, String.class, "email", 8, null) {
            @Override
            public void set(Employee object, String value) {
                object.setEmail(value);
            }

            @Override
            public String get(Employee object) {
                return object.getEmail();
            }
        };
        properties[9] = new AbstractProperty<Employee, String>(Employee.class, String.class, "fax", 9, null) {
            @Override
            public void set(Employee object, String value) {
                object.setFax(value);
            }

            @Override
            public String get(Employee object) {
                return object.getFax();
            }
        };
        properties[10] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark0", 10, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark0(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark0();
            }
        };
        properties[11] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark1", 11, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark1(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark1();
            }
        };
        properties[12] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark2", 12, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark2(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark2();
            }
        };
        properties[13] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark3", 13, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark3(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark3();
            }
        };

        properties[14] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark4", 14, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark4(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark4();
            }
        };

        properties[15] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark5", 15, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark5(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark5();
            }
        };

        properties[16] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark6", 16, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark6(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark6();
            }
        };

        properties[17] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark7", 17, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark7(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark7();
            }
        };

        properties[18] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark8", 18, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark8(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark8();
            }
        };

        properties[19] = new AbstractProperty<Employee, String>(Employee.class, String.class, "remark9", 19, null) {
            @Override
            public void set(Employee object, String value) {
                object.setRemark9(value);
            }

            @Override
            public String get(Employee object) {
                return object.getRemark9();
            }
        };

        properties[20] = new AbstractProperty<Employee, User>(Employee.class, User.class, "createUser", 20,
            UserAccessor2.INSTANCE) {
            @Override
            public void set(Employee object, User value) {
                object.setCreateUser(value);
            }

            @Override
            public User get(Employee object) {
                return object.getCreateUser();
            }
        };

        map.put("id", properties[0]);
        map.put("name", properties[1]);
        map.put("birthDate", properties[2]);
        map.put("gender", properties[3]);
        map.put("departmentId", properties[4]);
        map.put("organId", properties[5]);
        map.put("identityCard", properties[6]);
        map.put("mobile", properties[7]);
        map.put("email", properties[8]);
        map.put("fax", properties[9]);
        map.put("remark0", properties[10]);
        map.put("remark1", properties[11]);
        map.put("remark2", properties[12]);
        map.put("remark3", properties[13]);
        map.put("remark4", properties[14]);
        map.put("remark5", properties[15]);
        map.put("remark6", properties[16]);
        map.put("remark7", properties[17]);
        map.put("remark8", properties[18]);
        map.put("remark9", properties[19]);
        map.put("createUser", properties[20]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPropertyValue(Employee obj, int index, Object value) {
        properties[index].set(obj, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPropertyValue(Employee obj, String name, Object value) {
        map.get(name).set(obj, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getPropertyValue(Employee obj, int index) {
        return properties[index].get(obj);
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
        @SuppressWarnings("unchecked")
        Property<Object, Object> property = properties[indexes[0]];
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

    @Override
    @SuppressWarnings("unchecked")
    public Object getPropertyValue(Employee obj, String name) {
        return map.get(name).get(obj);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Property<Employee, ?> getProperty(int index) {
        return properties[index];
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
    public int getPropertyIndex(String name) {
        return getProperty(name).getIndex();
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
//private Long id;
//private String name;
//private LocalDate birthDate;
//private String gender;
//private Integer departmentId;
//private Integer organId;
//private String identityCard;
//private String mobile;
//private String email;
//private String fax;
//private String remark0;
//private String remark1;
//private String remark2;
//private String remark3;
//private String remark4;
//private String remark5;
//private String remark6;
//private String remark7;
//private String remark8;
//private String remark9;