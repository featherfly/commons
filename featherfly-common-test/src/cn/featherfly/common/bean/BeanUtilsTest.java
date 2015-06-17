package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * <p>
 * BeanUtilsTest 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class BeanUtilsTest {

    @Test
    public void testC2P() {
        P p = new P();
        C c = new C();
        c.setName("c->name");
        c.setN("c->n");
        BeanUtils.copyProperties(p, c);
        System.out.println(p.getName());
        assertEquals(p.getName(), c.getName());
    }

    @Test
    public void testP2C() {
        P p = new P();
        C c = new C();

        p.setName("p->name");

        BeanUtils.copyProperties(c, p);
        System.out.println(c.getName());
        assertEquals(p.getName(), c.getName());
    }
    
    @Test
    public void testMerge() {
        C c = new C();
        D d = new D();
        
        c.setN("c-N");
        c.setName("c->name");
        d.setName("d->name");
        d.setAge(18);
        
        BeanUtils.mergeProperties(d, c);
        System.out.println("d -> " + BeanUtils.toMap(d));
        System.out.println("c -> " + BeanUtils.toMap(c));
        assertEquals(d.getName(), c.getName());
        
        c.setN("c-N");
        c.setName("c->name");
        d.setName("d->name");
        d.setAge(18);
        BeanUtils.mergeProperties(c, d);
        System.out.println("c -> " + BeanUtils.toMap(c));
        System.out.println("d -> " + BeanUtils.toMap(d));
        assertEquals(d.getName(), c.getName());
    }
}

class P {

    private String name;

    /**
     * 返回name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

}

class D {
    private String name;

    private Integer age;

    /**
     * 返回name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回age
     * 
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age
     * 
     * @param age
     *            age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

}

class C extends P {
    private String n;

    /**
     * 返回n
     * 
     * @return n
     */
    public String getN() {
        return n;
    }

    /**
     * 设置n
     * 
     * @param n
     *            n
     */
    public void setN(String n) {
        this.n = n;
    }
}