
package cn.featherfly.common.bean.vo;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import cn.featherfly.common.bean.BeanProperty;

public class Student extends Person{
	
    private String classRoom;
    
    private Teacher teacher;

    /**
     * 返回classRoom
     * @return classRoom
     */
    public String getClassRoom() {
        return classRoom;
    }

    /**
     * 设置classRoom
     * @param classRoom classRoom
     */
    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    /**
     * 返回teacher
     * @return teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * 设置teacher
     * @param teacher teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public static void main(String[] args) {
        for (Method method : Student.class.getMethods()) {
            System.out.println(method.getName() + "  " + method.getDeclaringClass().getName());
            System.out.println("return type " + method.getReturnType());            
        }
        Method m;
        try {            
            m = Student.class.getMethod("setTeacher", new Class[] {Teacher.class});
            System.out.println("return type " + m.getReturnType());
        } catch (Exception e) {
            // YUFEI_TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
