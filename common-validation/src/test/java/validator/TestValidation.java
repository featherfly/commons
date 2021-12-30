
/*
 * All rights Reserved, Designed By zhongj
 * @Title: TestValidation.java
 * @Package cn.featherfly.hammer.sqldb
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-20 17:27:20
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package validator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableValidator;

import org.hibernate.validator.HibernateValidator;

/**
 * TestValidation.
 *
 * @author zhongj
 */
public class TestValidation {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        ExecutableValidator executableValidator = (ExecutableValidator) Validation.byProvider(HibernateValidator.class)
                .configure().failFast(false).buildValidatorFactory().getValidator();
        Car object = new Car("Morris");
        Method method = Car.class.getMethod("drive", int.class);
        Object[] parameterValues = { 80 };
        Set<ConstraintViolation<Car>> violations = executableValidator.validateParameters(object, method,
                parameterValues);
        for (ConstraintViolation<Car> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }

    }
}

class Car {

    public Car(@NotNull String manufacturer) {
        //...
    }

    public void drive(@Max(75) int speedInMph) {
        //...
    }

    @Size(min = 1)
    public List<String> getPassengers() {
        //...
        return Collections.emptyList();
    }
}
