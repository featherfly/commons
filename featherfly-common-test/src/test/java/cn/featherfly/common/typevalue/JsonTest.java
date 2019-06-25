//
//package cn.featherfly.common.typevalue;
//
//import java.io.IOException;
//
//import cn.featherfly.common.structure.typevalue.TypeLong;
//import cn.featherfly.common.structure.typevalue.TypeString;
//import cn.featherfly.common.structure.typevalue.jackson.TypeValueDeserializer;
//import cn.featherfly.common.structure.typevalue.jackson.TypeValueSerializer;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//
///**
// * <p>
// * JsonTest
// * 类的说明放这里
// * </p>
// * 
// * @author zhongj
// */
//public class JsonTest {
//    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(TypeLong.class, new TypeValueSerializer<TypeLong>());
//        module.addDeserializer(TypeLong.class, new TypeValueDeserializer<TypeLong>(TypeLong.class));
//        module.addSerializer(TypeString.class, new TypeValueSerializer<TypeString>());
//        module.addDeserializer(TypeString.class, new TypeValueDeserializer<TypeString>(TypeString.class));
//        objectMapper.registerModule(module);
//        User user = new User();
//        user.id = new UserId(1l);
//        user.age = new TypeLong(18l);
//        user.name = new TypeString("yufei");
//        
//        String result = objectMapper.writerFor(User.class).writeValueAsString(user);
//        System.out.println(result);
//        
//        User user2 = objectMapper.readerFor(User.class).readValue(objectMapper.writerFor(User.class).writeValueAsString(user));
//        System.out.println(user2.name);
//        System.out.println(user2.id);
//        System.out.println(user2.age);
//    }
//}
