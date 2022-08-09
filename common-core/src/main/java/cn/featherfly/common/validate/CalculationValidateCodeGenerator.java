/*
 * All rights Reserved, Designed By zhongj
 * @Title: CountValidateCodeGenerator.java
 * @Package cn.featherfly.common.validate
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2022年8月9日 下午5:36:11
 * @version V1.0
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */

package cn.featherfly.common.validate;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.enums.CalculationOperator;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Randoms;

/**
 * <p>
 * VerifyCodeGenerator
 * </p>
 * .
 *
 * @author zhongj
 */
public class CalculationValidateCodeGenerator implements ValidateCodeGenerator {

    /** The numbers. */
    private List<Integer> numbers = new ArrayList<>();

    /** The operators. */
    private List<CalculationOperator> operators = new ArrayList<>();

    /** The count times. */
    private int calculationTimes = 1;

    /**
     * Instantiates a new count validate code generator.
     */
    public CalculationValidateCodeGenerator() {
        this(1);
    }

    /**
     * Instantiates a new count validate code generator.
     *
     * @param calculationTimes the calculation times
     */
    public CalculationValidateCodeGenerator(int calculationTimes) {
        this(calculationTimes, CalculationOperator.PLUS, CalculationOperator.MULTIPLY);
    }

    /**
     * Instantiates a new count validate code generator.
     *
     * @param calculationTimes     the calculation times
     * @param calculationOperators the calculation operators
     */
    public CalculationValidateCodeGenerator(int calculationTimes, CalculationOperator... calculationOperators) {
        super();
        this.calculationTimes = calculationTimes;
        for (int i = 1; i < 10; i++) {
            numbers.add(i);
        }
        CollectionUtils.addAll(operators, calculationOperators);
    }

    /**
     * calculate.
     *
     * @param countTimes the count times
     * @return the validate code
     */
    private ValidateCode calculate(int calculationTimes) {
        int result = Randoms.get(numbers);
        String show = result + "";
        for (int i = 0; i < calculationTimes; i++) {
            CalculationOperator operator = Randoms.get(operators);
            int nextNumber = Randoms.get(numbers);
            show = show + operator.getSymbol() + nextNumber;
            switch (operator) {
                case PLUS:
                    result = result + nextNumber;
                    break;
                case SUBTRACT:
                    result = result - nextNumber;
                    break;
                case MULTIPLY:
                    result = result * nextNumber;
                    break;
                case DIVIDE:
                    result = result / nextNumber;
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
        return calculate(calculationTimes);
    }

}
