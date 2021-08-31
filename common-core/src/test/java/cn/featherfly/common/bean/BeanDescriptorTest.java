
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Optional;

import org.testng.Assert;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.vo.Address;
import cn.featherfly.common.bean.vo.ClassRoom;
import cn.featherfly.common.bean.vo.Person;
import cn.featherfly.common.bean.vo.Student;
import cn.featherfly.common.bean.vo.User;
import cn.featherfly.common.bean.vo.Zipcode;
import cn.featherfly.common.lang.vo.GenericTest;

public class BeanDescriptorTest {
    @Test
    public void test1() {
        User user = new User();
        String name = "yufei";
        int age = 20;
        boolean available = false;
        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
        BeanProperty pName = bd.getBeanProperty("name");
        BeanProperty pAge = bd.getBeanProperty("age");
        BeanProperty pAva = bd.getBeanProperty("available");
        pAge.setValue(user, age);
        pName.setValue(user, name);
        pAva.setValue(user, available);
        assertEquals(name, pName.getValue(user));
        assertEquals(age, pAge.getValue(user));
        //		assertEquals(available, pAva.getValue(user));

        pName = bd.getBeanProperty(User::getName);
        pAge = bd.getBeanProperty(User::getAge);
        pAva = bd.getBeanProperty(User::getAvailable);

        pName = bd.getBeanProperty(User::getName);
        pAge = bd.getBeanProperty(user::getAge);
        pAva = bd.getBeanProperty(user::setAvailable);

        assertEquals(name, pName.getValue(user));
        assertEquals(age, pAge.getValue(user));

        name = "featherfly";
        bd.setProperty(user, "name", name);
        assertEquals(name, bd.getProperty(user, "name"));
    }

    @Test
    public void test2() {
        User user = new User();
        String name = "yufei";

        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
        BeanProperty pName = bd.getBeanProperty("name");
        pName.setValue(user, name);
        assertEquals(name, pName.getValue(user));

        name = "featherfly";
        bd.setProperty(user, "name", name);
        assertEquals(name, bd.getProperty(user, "name"));
    }

    @Test
    public void test3() {
        String c = "610000";

        User user = new User();
        BeanDescriptor<User> bdUser = BeanDescriptor.getBeanDescriptor(User.class);
        assertEquals(bdUser.getProperty(user, "address.zipcode.code"), null);
        bdUser.setProperty(user, "address.zipcode.code", c);
        assertEquals(bdUser.getProperty(user, "address.zipcode.code"), c);
    }

    @Test
    public void test4() {
        String name = "yufei";
        String idCard = "1984";

        Person p = new Person();
        p.setName(name);
        p.setIdCard(idCard);

        BeanDescriptor<Person> bdPerson = BeanDescriptor.getBeanDescriptor(Person.class);
        assertEquals(bdPerson.getProperty(p, "idCard"), idCard);

        assertEquals(bdPerson.getProperty(p, "name"), name);

        Iterator<BeanProperty<?>> iter = bdPerson.getBeanProperties().iterator();
        while (iter.hasNext()) {
            BeanProperty bp = iter.next();
            System.out.println(bp.getName());
        }
    }

    @Test
    public void test5() {
        User user = new User();
        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);
        bd.addProperty(user, "names", "yufei");
        bd.addProperty(user, "names", "yi");
        assertEquals(user.getNames().size(), 2);
        System.out.println(user.getNames());

        bd.addProperty(user, "addresses", new Address());
        bd.addProperty(user, "addresses", new Address());

        assertEquals(user.getAddresses().size(), 2);
        System.out.println(user.getAddresses());
    }

    public static void main(String[] args) {
        User user = new User();
        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);

        System.out.println(bd.getBeanProperty("addresses").getType());
        System.out.println(bd.getBeanProperty("addresses").getField().getGenericType().getClass());
        System.out.println(bd.getBeanProperty("addresses").getField().getGenericType());
        if (bd.getBeanProperty("addresses").getField().getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) bd.getBeanProperty("addresses").getField().getGenericType();
            System.out.println(pt.getRawType());
            System.out.println(pt.getOwnerType());
            System.out.println(pt.getActualTypeArguments()[0]);
        }
        //		bd.setProperty(user, "addresses.name", "成都市");

        //		System.out.println(user.getAddresses());
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    public void test6() {
        User user = new User();
        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);

        // 直接给list中的对象设置参数需要使用addresses[0].name等方式，目前不支持
        bd.setProperty(user, "addresses.name", "成都市");

        System.out.println(user.getAddresses());
    }

    @Test
    public void testGeneric() {
        ClassRoom classRoom = new ClassRoom();
        BeanDescriptor<ClassRoom> bd = BeanDescriptor.getBeanDescriptor(ClassRoom.class);

        System.out.println(bd.getBeanProperty("person").getType());
        Assert.assertEquals(bd.getBeanProperty("person").getType(), Student.class);

        Student s = new Student();
        s.setAge(11);

        bd.setProperty(classRoom, "person", s);
        Assert.assertEquals(classRoom.getPerson().getAge(), s.getAge());
    }

    @Test
    public void test() {
        java.beans.BeanDescriptor bd = new java.beans.BeanDescriptor(User.class);
        String addName = "人民南路";
        // 只是在放在hashtable中而已，并没有和具体的对象绑定
        bd.setValue("address.name", addName);
        assertEquals(addName, bd.getValue("address.name"));
    }

    @Test
    public void testOptional() {
        BeanDescriptor<User> bd = BeanDescriptor.getBeanDescriptor(User.class);

        User user = new User();
        BeanProperty<Optional<String>> username = bd.getBeanProperty("username");
        System.out.println(username.getType());
        Assert.assertEquals(username.getType(), Optional.class);

        bd.setProperty(user, "username", "yufei");

        assertEquals(user.getUsername().get(), "yufei");

        assertEquals(username.getValue(user), user.getUsername());

        username.setValue(user, "featherfly");

        assertEquals(user.getUsername().get(), "featherfly");

        assertEquals(username.getValue(user), user.getUsername());

    }

    @Test
    public void testGeneric2() {
        BeanDescriptor<GenericTest> bd = BeanDescriptor.getBeanDescriptor(GenericTest.class);

        System.out.println(bd.getBeanProperty("id"));

    }

    @Test
    public void testNested() {
        Address address = new Address();
        String pn = "zipcode.code";
        String code = "610000";
        address.setZipcode(new Zipcode(code));
        BeanDescriptor<Address> bd = BeanDescriptor.getBeanDescriptor(Address.class);
        BeanProperty<Object> bp = bd.getBeanProperty(pn);
        assertEquals(bp.getName(), "code");
        assertEquals(BeanUtils.getProperty(address, pn), code);
    }

}
