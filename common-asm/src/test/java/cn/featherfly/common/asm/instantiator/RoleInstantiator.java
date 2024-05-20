
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:43:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm.instantiator;

import cn.featherfly.common.bean.Instantiator;

/**
 * RoleCreator.
 *
 * @author zhongj
 */
public class RoleInstantiator implements Instantiator<Role> {

    private Class<Role> type;

    /**
     * {@inheritDoc}
     */
    @Override
    public Role instantiate() {
        return new Role();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Role> getType() {
        return Role.class;
    }

    public Class<Role> getType2() {
        return type;
    }
}
