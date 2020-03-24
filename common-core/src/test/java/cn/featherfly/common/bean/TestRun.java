
package cn.featherfly.common.bean;

import java.util.Iterator;

import cn.featherfly.common.bean.vo.Person;

/**
 */
public class TestRun {
    public static void main(String[] args) {
        BeanDescriptor<Person> bd = BeanDescriptor.getBeanDescriptor(Person.class);
        Iterator<BeanProperty<?>> iter = bd.getBeanProperties().iterator();
        Person person = new Person();
        while (iter.hasNext()) {
            BeanProperty<?> p = iter.next();
            //			System.out.println(p + " --- " + p.getExtendsFrom().getName());
        }
        bd.getBeanProperty("name").setValue(person, "yufei");
        System.out.println(person.getName());
    }
}
