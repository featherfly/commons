
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

import cn.featherfly.common.bean.Property;

/**
 * EmployeeProperties.
 *
 * @author zhongj
 */
public class EmployeeVisitor {

    private Property<Employee, Object>[] properties;

    private Map<String, Property<Employee, Object>> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public EmployeeVisitor() {
        properties = new Property[20];

        properties[0] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setId((Long) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getId();
            }
        };

        properties[1] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setName((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getName();
            }
        };

        properties[2] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setBirthDate((LocalDate) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getBirthDate();
            }
        };

        properties[3] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setGender((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getGender();
            }
        };

        properties[3] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setGender((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getGender();
            }
        };

        properties[4] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setDepartmentId((Integer) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getDepartmentId();
            }
        };

        properties[5] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setOrganId((Integer) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getOrganId();
            }
        };
        properties[6] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setIdentityCard((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getIdentityCard();
            }
        };
        properties[7] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setMobile((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getMobile();
            }
        };
        properties[8] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setEmail((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getEmail();
            }
        };
        properties[9] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setFax((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getFax();
            }
        };
        properties[10] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark0((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark0();
            }
        };
        properties[11] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark1((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark1();
            }
        };
        properties[12] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark2((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark2();
            }
        };
        properties[13] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark3((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark3();
            }
        };

        properties[14] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark4((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark4();
            }
        };

        properties[15] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark5((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark5();
            }
        };

        properties[16] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark6((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark6();
            }
        };

        properties[17] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark7((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark7();
            }
        };

        properties[18] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark8((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark8();
            }
        };

        properties[19] = new Property<Employee, Object>() {
            @Override
            public void set(Employee object, Object value) {
                object.setRemark9((String) value);
            }

            @Override
            public Object get(Employee object) {
                return object.getRemark9();
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
    }

    public void setProperty(Employee obj, int index, Object value) {
        properties[index].set(obj, value);
    }

    public void setProperty(Employee obj, String name, Object value) {
        map.get(name).set(obj, value);
    }

    public Object getProperty(Employee obj, int index) {
        return properties[index].get(obj);
    }

    public Object getProperty(Employee obj, String name) {
        return map.get(name).get(obj);
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