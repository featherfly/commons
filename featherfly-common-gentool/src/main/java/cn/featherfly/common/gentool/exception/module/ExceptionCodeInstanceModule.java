package cn.featherfly.common.gentool.exception.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.WordUtils;

public class ExceptionCodeInstanceModule {

    // private ExceptionCodeModule exceptionCodeModule;

    private Integer num;

    private String key;
    
    private String name;

    private Map<Locale, String> messages = new HashMap<>();

    private List<ArgumentModule> argus = new ArrayList<>(0);

    /**
     * 返回num
     * 
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置num
     * 
     * @param num
     *            num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 返回arguss
     * 
     * @return arguss
     */
    public List<ArgumentModule> getArgus() {
        return argus;
    }

    /**
     * 设置arguss
     * 
     * @param arguss
     *            arguss
     */
    public void setArgus(List<ArgumentModule> arguss) {
        this.argus = arguss;
    }

    /**
     * 返回key
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key
     * 
     * @param key
     *            key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 返回messages
     * 
     * @return messages
     */
    public Map<Locale, String> getMessages() {
        return messages;
    }

    /**
     * 设置messages
     * 
     * @param messages
     *            messages
     */
    public void setMessages(Map<Locale, String> messages) {
        this.messages = messages;
    }
            
    /**
     * 设置name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        if (LangUtils.isEmpty(name)) {            
            if (key.contains(Chars.UNDER_LINE)) {
                name = WordUtils.parseToUpperFirst(key, Chars.UNDER_LINE.toCharArray()[0]);                
            } else if (key.contains(Chars.DOT)) {
                name = WordUtils.parseToUpperFirst(key, Chars.DOT.toCharArray()[0]);
            } else {
                name = key;
            }
            name = WordUtils.upperCaseFirst(name);
        }
        return name;
    }
}