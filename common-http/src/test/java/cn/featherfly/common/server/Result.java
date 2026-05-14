
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-05-12 16:28:12
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.server;

import java.util.Map.Entry;

/**
 * Response.
 *
 * @author zhongj
 */
public class Result {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + "]";
    }

    public static void main(String[] args) {
        for (Entry<Object, Object> entrySet : System.getProperties().entrySet()) {
            System.out.println(entrySet.getKey() + "    " + entrySet.getValue());
        }
    }
}
