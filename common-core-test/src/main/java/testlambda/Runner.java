
package testlambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import testlambda.Lambdas.SerializableSupplierLambdaInfo;
import testlambda.vo.User2;

/**
 * <p>
 * Runner
 * </p>
 *
 * @author zhongj
 */
@SpringBootApplication(scanBasePackages = { "com.cdzkdc" })
//@ComponentScan(basePackages = { "com.cdzkdc" })
public class Runner {

    public static void main(String[] args) {

        User2 user = new User2();
        user.setAge(18);

        SerializableSupplierLambdaInfo<Integer> info = Lambdas.getSerializableSupplierLambdaInfo(user::getAge);
        System.out.println(info.getValue());

        System.out.println(info.getSerializedLambdaInfo().getMethodInstanceClassName());
        System.out.println(info.getSerializedLambdaInfo().getMethodDeclaredClassName());

        SpringApplication.run(Runner.class, args);
    }
}
