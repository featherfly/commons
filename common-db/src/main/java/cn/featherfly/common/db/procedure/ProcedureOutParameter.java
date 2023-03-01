
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ProcedureOutParameter.java
 * @Package cn.featherfly.common.db.procedure
 * @Description: ProcedureOutParameter
 * @author: zhongj
 * @date: 2023-03-01 15:35:01
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.procedure;

/**
 * ProcedureOutParameter.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class ProcedureOutParameter<T> extends AbstractProcedureParameter<T> {

    /**
     * Instantiates a new procedure out parameter.
     *
     * @param type the type
     */
    public ProcedureOutParameter(Class<T> type) {
        super(type);
    }

    /**
     * Instantiates a new procedure out parameter.
     *
     * @param value the value
     */
    public ProcedureOutParameter(T value) {
        super(value);
    }

}
