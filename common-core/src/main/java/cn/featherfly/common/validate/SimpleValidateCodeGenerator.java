
package cn.featherfly.common.validate;

import java.util.Random;

/**
 * <p>
 * VerifyCodeGenerator
 * </p>
 * .
 *
 * @author zhongj
 */
public class SimpleValidateCodeGenerator implements ValidateCodeGenerator {

    /** VERIFY_CODES. */
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /** The size. */
    private int size;

    /**
     * Instantiates a new simple validate code generator.
     */
    public SimpleValidateCodeGenerator() {
    }

    /**
     * Instantiates a new simple validate code generator.
     *
     * @param size the size
     */
    public SimpleValidateCodeGenerator(int size) {
        super();
        this.size = size;
    }

    /**
     * 返回size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置size.
     *
     * @param size size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 使用系统默认字符源生成验证码.
     *
     * @param verifySize 验证码长度
     * @return verifyCode
     */
    public String generate(int verifySize) {
        return generate(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码.
     *
     * @param size    验证码长度
     * @param sources 验证码字符源
     * @return verifyCode
     */
    public String generate(int size, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidateCode generate() {
        String code = generate(size);
        return new ValidateCode(code, code);
    }
}
