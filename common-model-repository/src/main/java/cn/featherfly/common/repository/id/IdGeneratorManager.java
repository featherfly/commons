
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 18:15:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import cn.featherfly.common.lang.pool.PoolImpl;

/**
 * GeneratorManager.
 *
 * @author zhongj
 */
public class IdGeneratorManager extends PoolImpl<String, IdGenerator, IdGeneratorManager> {

    public static final String ASSIGN = "assign";
    public static final String ASSIGN_ORDERED = "assignOrdered";
    public static final String UUID = "uuid";

    /**
     */
    public IdGeneratorManager() {
        add(IdGeneratorManager.UUID, new UUIDGenerator());
        add(IdGeneratorManager.ASSIGN, new AssignGenerator());
        add(IdGeneratorManager.ASSIGN_ORDERED, new AssignOrderedGenerator());
    }
}
