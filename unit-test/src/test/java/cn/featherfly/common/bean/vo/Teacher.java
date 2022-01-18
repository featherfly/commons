
package cn.featherfly.common.bean.vo;

import java.util.ArrayList;
import java.util.List;



public class Teacher extends Person{
	
    private List<String> classRooms = new ArrayList<>();

    /**
     * 返回classRooms
     * @return classRooms
     */
    public List<String> getClassRooms() {
        return classRooms;
    }

    /**
     * 设置classRooms
     * @param classRooms classRooms
     */
    public void setClassRooms(List<String> classRooms) {
        this.classRooms = classRooms;
    }

    
    
}
