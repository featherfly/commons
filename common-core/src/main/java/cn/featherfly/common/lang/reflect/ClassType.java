
package cn.featherfly.common.lang.reflect;

/**
 * GenericClass .
 *
 * @author zhongj
 * @param <T> 类型
 */
//YUFEI_TODO 先使用这个兼容模式，后续删除GenericClass
public class ClassType<T> extends GenericClass<T> implements Type<T> {

    //    private Class<T> c;

    /**
     * Instantiates a new generic class.
     *
     * @param c classType
     */
    public ClassType(Class<T> c) {
        super(c);
        //        AssertIllegalArgument.isNotNull(c, "Class<T> c");
        //        this.c = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getType().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != ClassType.class) {
            return false;
        }
        return getType().equals(((ClassType<?>) obj).getType());
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public Class<T> getType() {
    //        return c;
    //    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public int hashCode() {
    //        return c.hashCode();
    //    }
    //
    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public boolean equals(Object obj) {
    //        if (obj == null) {
    //            return false;
    //        }
    //        if (obj.getClass() != ClassType.class) {
    //            return false;
    //        }
    //        return c.equals(((ClassType<?>) obj).c);
    //    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public String getTypeName() {
    //        return c.getName();
    //    }
}
