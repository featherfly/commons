
package cn.featherfly.common.lang.vo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * GenericSuper
 * </p>
 *
 * @author zhongj
 */
public abstract class GenericSuper<T, R, ID extends Number> extends AbstractSuperGet<Short, Byte>
        implements Function<T, R>, UserSuplier {

    public ID id;

    public List<ID> listId;

    public Map<T, R> map;

    /**
     * 返回map
     *
     * @return map
     */
    public Map<T, R> getMap() {
        return map;
    }

    /**
     * 设置map
     *
     * @param map map
     */
    public void setMap(Map<T, R> map) {
        this.map = map;
    }

    /**
     * 返回id
     *
     * @return id
     */
    public ID getId() {
        return id;
    }

    public <N> N getName() {
        return null;
    }

    public <N> N getName(N n) {
        return null;
    }

    @Override
    public R apply(T t) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User get() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(ID id) {
        this.id = id;
    }

    public String string() {
        return null;
    }

    public abstract List<T> listInteger();

    public abstract List<R> listString();

    public abstract List<User> listUser();

    public List<ID> listId() {
        return null;
    }

    public void setListId(List<ID> list) {

    }

    public <N extends Number> List<N> listNumber() {
        return null;
    }

    public <N extends Number> N number() {
        return null;
    }

    public <N extends Number> N number(N n) {
        return null;
    }

    public abstract <G> List<G> listGeneric();

    public abstract <G> List<G> listGeneric(G g);

    public abstract Function<T, R> function();

    public void set(T t, R r, ID id) {
    }

    public void set2(List<ID> idList, Map<T, R> map) {

    }

    //    <S extends GenericSuper<T, R>> S get2();
}
