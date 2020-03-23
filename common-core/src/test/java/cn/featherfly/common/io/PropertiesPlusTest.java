
package cn.featherfly.common.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.testng.annotations.Test;

import cn.featherfly.common.constant.Charsets;

/**
 * <p>
 * PropertiesTest
 * </p>
 *
 * @author zhongj
 */
public class PropertiesPlusTest {

    Charset charset = StandardCharsets.UTF_8;

    @Test
    public void writeAndReadWithCharset() throws IOException, InterruptedException {
        String filePath = Properties.class.getResource("/").getPath() + "writeAndReadWithCharset.properties";
        Properties pp = new PropertiesPlus(charset);
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Thread.sleep(100);

        Properties p = new PropertiesPlus(charset);
        p.load(new FileInputStream(filePath));
        showProperties(p);

        System.out.println(p.listAll());

        p.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        p.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(p.listAll());

        os = new FileOutputStream(filePath);
        p.store(os);
        os.close();
    }

    @Test
    public void writeAndReadWithoutCharset() throws IOException {
        String filePath = Properties.class.getResource("/").getPath() + "writeAndReadWithOutCharset.properties";
        Properties pp = new PropertiesPlus();
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Properties p = new PropertiesPlus();
        p.load(new FileInputStream(filePath));
        showProperties(p);

        p.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        p.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(p.listAll());

        os = new FileOutputStream(filePath);
        p.store(os);
        os.close();
    }

    @Test
    public void writeWithCharsetAndSmartRead() throws IOException {
        String filePath = Properties.class.getResource("/").getPath() + "writeWithCharsetAndSmartRead.properties";
        Properties pp = new PropertiesPlus(Charsets.GBK);
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Properties p = new PropertiesPlus();
        p.load(new FileInputStream(filePath));
        showProperties(p);

        p.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        p.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(p.listAll());

        os = new FileOutputStream(filePath);
        p.store(os);
        os.close();
    }

    @Test
    public void readCommonProperties() throws IOException {
        String filePath = Properties.class.getResource("/").getPath() + "../test/test.properties";
        Properties p = new PropertiesPlus(StandardCharsets.UTF_8);
        p.load(new FileInputStream(filePath));
        System.out.println(p.getProperty("isDirectory"));
        System.out.println(p.getPropertyPart("isDirectory"));
        System.out.println(p.getProperty("isExists"));
        System.out.println(p.getPropertyPart("isExists"));
        System.out.println(p.getProperty("isFile"));
        System.out.println(p.getPropertyPart("isFile"));
        System.out.println(p.getProperty("isGt"));
        System.out.println(p.getPropertyPart("isGt"));

        //        System.out.println(p.listAll());

        //        p.setProperty("descp", "描述说明", "这个是读取文件后新加入的");
        //
        //        p.setProperty("name", "钟冀11", "这个是读取文件后修改的");
        //        FileOutputStream os = new FileOutputStream(filePath);
        //        p.store(os);
        //        os.close();
    }

    private void setProperties(Properties pp) {
        pp.setProperty("name", "钟冀", "姓名");
        pp.setProperty("na me", "钟冀", "姓名na me");
        pp.setProperty("na=me", "钟冀", "姓名na=me");
        pp.setProperty("na!me", "钟冀", "姓名na!me");
        pp.setProperty("na#me", "钟冀", "姓名na#me");
        pp.setProperty("na\tme", "钟冀", "姓名na\\tme");
        pp.setProperty("na\nme", "钟冀", "姓名na\\nme");
        pp.setProperty("age", "18", "年龄");
        pp.setProperty("语言", "中文", "原因说明");
        pp.setProperty("username", "yufei");
        //        pp.comment("测试添加注释11", "测试添加注释12", "测试添加注释13");
        //        pp.comment("测试添加注释21", "测试添加注释22");
    }

    private void showProperties(Properties p) {
        System.out.println(p.getProperty("name"));
        System.out.println(p.getPropertyPart("name"));
        System.out.println(p.getProperty("age"));
        System.out.println(p.getPropertyPart("age"));
        System.out.println(p.getProperty("语言"));
        System.out.println(p.getPropertyPart("语言"));
        System.out.println(p.getProperty("username"));
        System.out.println(p.getPropertyPart("username"));
    }
}
