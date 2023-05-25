package cn.featherfly.common.validate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;

/**
 * ValidateCodeUtils.
 *
 * @author zhongj
 * @version 1.0
 * @since 1.6
 */
public final class ValidateCodeUtils {

    // 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写
    private static final SimpleValidateCodeGenerator CODE_GENERATOR = new SimpleValidateCodeGenerator();

    private static Random random = new Random();

    /**
     *
     */
    private ValidateCodeUtils() {
        super();
    }

    /**
     * 使用系统默认字符源生成验证码.
     *
     * @param verifySize 验证码长度
     * @return verifyCode
     */
    public static String generateVerifyCode(int verifySize) {
        return CODE_GENERATOR.generate(verifySize);
    }

    /**
     * 使用指定源生成验证码.
     *
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     * @return verifyCode
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        return CODE_GENERATOR.generate(verifySize, sources);
    }

    /**
     * 生成随机验证码文件,并返回验证码值.
     *
     * @param w          width
     * @param h          height
     * @param outputFile outputFile
     * @param verifySize verifySize
     * @return verifyCode
     * @throws IOException IOException
     */
    public static String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, outputFile, verifyCode);
        return verifyCode;
    }

    /**
     * 输出随机验证码图片流,并返回验证码值.
     *
     * @param w          width
     * @param h          height
     * @param os         OutputStream
     * @param verifySize verifySize
     * @return verifyCode
     * @throws IOException IOException
     */
    public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, os, verifyCode);
        return verifyCode;
    }

    /**
     * 生成指定验证码图像文件.
     *
     * @param w          width
     * @param h          height
     * @param outputFile outputFile
     * @param code       verifyCode
     * @throws IOException IOException
     */
    public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
        if (outputFile == null) {
            return;
        }
        File dir = outputFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            outputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outputFile);
            outputImage(w, h, fos, code);
            fos.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 输出指定验证码图片流.
     *
     * @param w    width
     * @param h    height
     * @param os   OutputStream
     * @param code verifyCode
     * @throws IOException IOException
     */
    public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
        //        int fontSize = h - 4;
        //        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        outputImage(code, w, h, os, "jpg", (width, height) -> new Font("Algerian", Font.ITALIC, height - 4));
    }

    /**
     * 输出指定验证码图片流.
     *
     * @param code         verifyCode
     * @param w            width
     * @param h            height
     * @param os           OutputStream
     * @param imageFormat  the image format
     * @param fontFunction the font function
     * @throws IOException IOException
     */
    public static void outputImage(String code, int w, int h, OutputStream os, String imageFormat,
            BiFunction<Integer, Integer, Font> fontFunction) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        // 设置边框色
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);

        Color c = getRandColor(200, 250);
        // 设置背景色
        g2.setColor(c);
        g2.fillRect(0, 2, w, h - 4);

        // 绘制干扰线
        Random random = new Random();
        // 设置线条的颜色
        g2.setColor(getRandColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        float yawpRate = 0.05f;
        // 噪声率
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        // 使图片扭曲
        shear(g2, w, h, c);

        g2.setColor(getRandColor(100, 160));
        //        int fontSize = h - 4;
        //        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        Font font = fontFunction.apply(w, h);
        int fontSize = font.getSize();
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    w / verifySize * i + fontSize / 2, h / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, (w - 10) / verifySize * i + 5, h / 2 + fontSize / 2 - 10);
        }

        g2.dispose();
        ImageIO.write(image, imageFormat, os);
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (period >> 1) * Math.sin((double) i / (double) period + 6.2831853071795862D * phase / frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (period >> 1) * Math.sin((double) i / (double) period + 6.2831853071795862D * phase / frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }
    // public static void main(String[] args) throws IOException{
    // File dir = new File("F:/verifies");
    // int w = 200, h = 80;
    // for(int i = 0; i < 50; i++){
    // String verifyCode = generateVerifyCode(4);
    // File file = new File(dir, verifyCode + ".jpg");
    // outputImage(w, h, file, verifyCode);
    // }
    // }
}
