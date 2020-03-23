
package cn.featherfly.common.validate;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.RandomUtils;

/**
 * <p>
 * VerifyCodeGenerator
 * </p>
 *
 * @author zhongj
 */
public class CountValidateCodeGenerator implements ValidateCodeGenerator {

    private List<Integer> numbers = new ArrayList<>();

    private List<String> operators = new ArrayList<>();

    private int countTimes = 1;

    /**
     */
    public CountValidateCodeGenerator() {
        this(1);
    }

    /**
     * @param countTimes
     */
    public CountValidateCodeGenerator(int countTimes) {
        super();
        this.countTimes = countTimes;
        for (int i = 1; i < 10; i++) {
            numbers.add(i);
        }
        CollectionUtils.addAll(operators, "+", "-");
        //        CollectionUtils.addAll(operators, "+", "-", "*", "/");
    }

    private ValidateCode count(int countTimes) {
        int result = RandomUtils.getRandom(numbers);
        String show = result + "";
        for (int i = 0; i < countTimes; i++) {
            String operator = RandomUtils.getRandom(operators);
            int nextNumber = RandomUtils.getRandom(numbers);
            show = show + operator + nextNumber;
            switch (operator) {
                case "+":
                    result = result + nextNumber;
                    break;
                case "-":
                    result = result - nextNumber;
                    break;
                //                case "*":
                //                    result = result * nextNumber;
                //                    break;
                //                case "/":
                //                    result = result / nextNumber;
            }
        }
        show = show + "=";
        return new ValidateCode(show, result + "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateCode generate() {
        return count(countTimes);
    }

}
