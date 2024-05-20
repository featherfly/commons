package impl;

public class Role {

    private Integer id;

    private String name;

    private String group;

    private int age;

    private boolean available;

    //    private String descp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //
    //    public String getDescp() {
    //        return descp;
    //    }
    //
    //    public void setDescp(String descp) {
    //        this.descp = descp;
    //    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}