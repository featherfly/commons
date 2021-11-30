
/*
 * All rights Reserved, Designed By zhongj
 * @Title: EnumConvertor.java
 * @Package cn.featherfly.common.model
 * @Description: enum convertor
 * @author: zhongj
 * @date: 2021-11-30 18:46:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * EnumConvertor.
 *
 * @author zhongj
 */
public interface EnumConvertor {

    <T extends Enum<T>> T toEnum(Class<T> toClass, Object object);
}
