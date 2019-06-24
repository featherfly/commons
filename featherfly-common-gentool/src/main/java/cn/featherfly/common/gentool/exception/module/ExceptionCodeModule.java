
package cn.featherfly.common.gentool.exception.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.featherfly.common.exception.SimpleLocalizedExceptionCode;

/**
 * <p>
 * ExceptionCodeModule
 * </p>
 * 
 * @author zhongj
 */
public class ExceptionCodeModule extends ClassModule {

    public static final Class<SimpleLocalizedExceptionCode> DEFAULT_PARENT = SimpleLocalizedExceptionCode.class;
    
    private String module;
    
    private List<ExceptionCodeInstanceModule> codes = new ArrayList<>(0);
    
    private Map<Locale, Properties> locales = new HashMap<>(); 

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getParent() {
        return super.getParent() != null
                ? super.getParent()
                : DEFAULT_PARENT;
    }
    
    /**
     * 返回codes
     * @return codes
     */
    public List<ExceptionCodeInstanceModule> getCodes() {
        return codes;
    }

    /**
     * 设置codes
     * @param codes codes
     */
    public void setCodes(List<ExceptionCodeInstanceModule> codes) {
        this.codes = codes;
    }

    /**
     * 返回module
     * @return module
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置module
     * @param module module
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 返回locales
     * @return locales
     */
    @JsonIgnore
    public Map<Locale, Properties> getLocales() {
        if (locales.isEmpty()) {
            generateLocales();
        }
        return locales;
    }
    
    /**
     * <p>
     * 生成locales
     * </p>
     */
    public void generateLocales() {
        locales.clear();        
        codes.forEach(code -> {
            code.getMessages().forEach((key, value) -> {
                Properties p = locales.get(key);
                if (p == null) {
                    p = new Properties();
                    locales.put(key, p);
                }                
                p.setProperty(code.getKey(), value);
            });
        });
    }
    
}
