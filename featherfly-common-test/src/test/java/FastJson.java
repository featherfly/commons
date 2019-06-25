


import org.apache.commons.lang.ObjectUtils;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * FastJson
 * 类的说明放这里
 * </p>
 * 
 * @author zhongj
 */
public class FastJson {
	public static void main(String[] args) {
		Errors errors = new Errors();
		errors.setMessage("message");
		errors.addErrorMessage("global error message");
		errors.addFieldError(new FieldError("name", "required"));
		errors.addFieldError(new FieldError("password", "required"));
		errors.addFieldError(new FieldError("password", "length must > 6"));
//		JSON.toJSONStringWithDateFormat(errors, "yyyy-MM-dd HH:mm:ss.SSS");
//		String json = JSON.toJSONString(errors, SerializerFeature.WriteDateUseDateFormat);
		String json = JSON.toJSONString(errors);
		System.out.println(json);
		
		System.out.println(
				JSON.parseObject(json, Errors.class));				
	}
}
