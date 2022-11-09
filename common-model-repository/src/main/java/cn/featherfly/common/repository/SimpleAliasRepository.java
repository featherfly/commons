
package cn.featherfly.common.repository;

/**
 * The Class SimpleAliasRepository.
 *
 * @author zhongj
 */
public class SimpleAliasRepository extends SimpleRepository implements AliasRepository {

    private String alias;

    /**
     * Instantiates a new simple alias repository.
     */
    public SimpleAliasRepository() {

    }

    /**
     * Instantiates a new simple alias repository.
     *
     * @param name name
     */
    public SimpleAliasRepository(String name) {
        super(name);
    }

    /**
     * Instantiates a new simple alias repository.
     *
     * @param name  name
     * @param alias alias
     */
    public SimpleAliasRepository(String name, String alias) {
        super(name);
        this.alias = alias;
    }

    /**
     * 返回alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置alias.
     *
     * @param alias alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alias() {
        return getAlias();
    }
}
