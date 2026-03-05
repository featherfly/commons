
package cn.featherfly.common.io;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.testng.annotations.Test;

/**
 * PropertiesTest.
 *
 * @author zhongj
 */
public class PropertiesPlusTest {

    Charset charset = StandardCharsets.UTF_8;

    @Test
    public void writeAndReadWithCharset() throws IOException, InterruptedException {
        String filePath = Properties.class.getResource("/").getPath()
            + "../test/writeAndReadWithCharset.jdk.properties";
        //        Properties pp = new PropertiesPlus(charset);
        Properties pp = new PropertiesPlus();
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Thread.sleep(100);

        //        Properties p = new PropertiesPlus(charset);
        Properties reader = new PropertiesPlus();
        reader.load(new FileInputStream(filePath));
        showProperties(reader);

        System.out.println(reader.listAll());

        reader.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        reader.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(reader.listAll());

        os = new FileOutputStream(filePath);
        reader.store(os);
        os.close();

        reader = new PropertiesImpl();
        reader.load(new FileInputStream(filePath));
        assertReset(reader);
        System.out.println(reader.listAll());
    }

    @Test
    public void writeAndReadWithoutCharset() throws IOException {
        String filePath = Properties.class.getResource("/").getPath()
            + "../test/writeAndReadWithOutCharset.jdk.properties";
        Properties pp = new PropertiesPlus();
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Properties reader = new PropertiesPlus();
        reader.load(new FileInputStream(filePath));
        showProperties(reader);

        reader.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        reader.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(reader.listAll());

        os = new FileOutputStream(filePath);
        reader.store(os);
        os.close();

        reader = new PropertiesImpl();
        reader.load(new FileInputStream(filePath));
        assertReset(reader);
        System.out.println(reader.listAll());
    }

    @Test
    public void writeWithCharsetAndSmartRead() throws IOException {
        String filePath = Properties.class.getResource("/").getPath()
            + "../test/writeWithCharsetAndSmartRead.jdk.properties";
        //        Properties pp = new PropertiesPlus(Charsets.GBK);
        Properties pp = new PropertiesPlus();
        setProperties(pp);
        OutputStream os = new FileOutputStream(filePath);
        pp.store(os);
        os.close();
        System.out.println(pp.listAll());

        Properties reader = new PropertiesPlus();
        reader.load(new FileInputStream(filePath));
        showProperties(reader);

        reader.setProperty("descp", "描述说明", "这个是读取文件后新加入的");

        reader.setProperty("name", "钟冀11", "这个是读取文件后修改的");

        System.out.println(reader.listAll());

        os = new FileOutputStream(filePath);
        reader.store(os);
        os.close();

        reader = new PropertiesImpl();
        reader.load(new FileInputStream(filePath));
        assertReset(reader);
        System.out.println(reader.listAll());
    }

    @Test
    public void readJdkProperties() throws IOException {
        String filePath = Properties.class.getResource("/").getPath() + "../test/test.properties";
        //        Properties p = new PropertiesPlus(StandardCharsets.UTF_8);
        Properties p = new PropertiesPlus();
        p.load(new FileInputStream(filePath));

        System.out.println(p.getProperty("isDirectory"));
        System.out.println(p.getPropertyPart("isDirectory"));
        System.out.println(p.getProperty("isExists"));
        System.out.println(p.getPropertyPart("isExists"));
        System.out.println(p.getProperty("isFile"));
        System.out.println(p.getPropertyPart("isFile"));
        System.out.println(p.getProperty("isGt"));
        System.out.println(p.getPropertyPart("isGt"));

        assertEquals(p.getProperty("isDirectory"), "{0}不能为null且{1}必须是目录类型");
        assertEquals(p.getProperty("isExists"), "{0}不能为null且文件{1}必须存在");
        assertEquals(p.getProperty("isFile"), "{0}不能为null且{1}必须是文件类型");
        assertEquals(p.getProperty("isGt"), "参数{2}的值{0}必须是大于{1}的整数");

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

        assertEquals(p.getProperty("name"), "钟冀");
        assertEquals(p.getPropertyPart("name").getComment(), "姓名");
        assertEquals(p.getProperty("age"), "18");
        assertEquals(p.getPropertyPart("age").getComment(), "年龄");
        assertEquals(p.getProperty("语言"), "中文");
        assertEquals(p.getPropertyPart("语言").getComment(), "原因说明");
        assertEquals(p.getProperty("username"), "yufei");
        assertEquals(p.getPropertyPart("username").getComment(), null);
    }

    private void assertReset(Properties p) {
        assertEquals(p.getProperty("name"), "钟冀11");
        assertEquals(p.getPropertyPart("name").getComment(), "这个是读取文件后修改的");
        assertEquals(p.getProperty("descp"), "描述说明");
        assertEquals(p.getPropertyPart("descp").getComment(), "这个是读取文件后新加入的");
    }
}
