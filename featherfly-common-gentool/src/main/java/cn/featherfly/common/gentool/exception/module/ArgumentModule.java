
package cn.featherfly.common.gentool.exception.module;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cn.featherfly.common.gentool.exception.jackson.JavaTypeDeserializer;

/**
 * <p>
 * ArgumentModule
 * </p>
 * 
 * @author zhongj
 */
public class ArgumentModule {

    @JsonDeserialize(using= JavaTypeDeserializer.class)
    private Class<?> type;
    
    private String name;
    
    /**
     * 
     */
    public ArgumentModule() {
    }

    /**
     * @param type
     * @param name
     */
    public ArgumentModule(Class<?> type, String name) {
        super();
        this.type = type;
        this.name = name;
    }

    /**
     * 返回type
     * @return type
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * 设置type
     * @param type type
     */
    public void setType(Class<?> type) {
        this.type = type;
    }

    /**
     * 返回name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}
