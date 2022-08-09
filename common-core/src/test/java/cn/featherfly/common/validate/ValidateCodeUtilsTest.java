package cn.featherfly.common.validate;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ValidateCodeUtilsTest.
 *
 * @author zhongj
 */
public class ValidateCodeUtilsTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int width = 128;
        int height = 36;

        CalculationValidateCodeGenerator generator = new CalculationValidateCodeGenerator(1);

        ValidateCode validateCode = generator.generate();

        System.out.println(validateCode);

        ValidateCodeUtils.outputImage(validateCode.getShow(), width, height, new FileOutputStream(new File("a.png")),
                "png", (w, h) -> new Font("微软雅黑", Font.PLAIN, height - 4));
    }
}
