package cn.featherfly.common.structure.typeid.jackson;

import java.io.IOException;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.structure.typeid.IdLong;
import cn.featherfly.common.structure.typeid.IdObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class IdObjectDeserializer<ID extends IdObject> extends JsonDeserializer<ID> {
    
    private Class<ID> type;
    
    /**
     * 
     */
    public IdObjectDeserializer(Class<ID> type) {
        this.type = type;
    }
    
    @Override
    public ID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return (ID) ClassUtils.newInstance(type, p.getText());
    }
    
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(IdLong.class, new IdObjectSerializer<IdLong>());
        module.addDeserializer(IdLong.class, new IdObjectDeserializer<IdLong>(IdLong.class));
        objectMapper.registerModule(module);
        User user = new User();
        user.id = new IdLong(1l);
        user.name = "yufei";
        
        String result = objectMapper.writerFor(User.class).writeValueAsString(user);
        System.out.println(result);
        
        User user2 = objectMapper.readerFor(User.class).readValue(objectMapper.writerFor(User.class).writeValueAsString(user));
        System.out.println(user2.name);
        System.out.println(user2.id);
    }
}

class User {
    IdLong id;
    
    String name;

    /**
     * 返回id
     * @return id
     */
    public IdLong getId() {
        return id;
    }

    /**
     * 设置id
     * @param id id
     */
    public void setId(IdLong id) {
        this.id = id;
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