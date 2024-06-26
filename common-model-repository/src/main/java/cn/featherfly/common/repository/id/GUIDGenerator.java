
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-31 17:34:31
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.id;

import cn.featherfly.common.lang.UUIDGenerator.Type;

/**
 * GUIDGenerator.
 *
 * @author zhongj
 */
public class GUIDGenerator extends UUIDGenerator {

    /**
     * Instantiates a new GUID generator.
     */
    public GUIDGenerator() {
        super(Type.UUID36);
    }
}
