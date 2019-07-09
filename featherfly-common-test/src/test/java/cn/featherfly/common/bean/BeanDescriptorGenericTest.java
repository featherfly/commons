
package cn.featherfly.common.bean;

import java.util.List;

import javax.xml.ws.BindingType;

import org.testng.Assert;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.vo.ClassRoom;
import cn.featherfly.common.bean.vo.Student;

public class BeanDescriptorGenericTest {
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

}
