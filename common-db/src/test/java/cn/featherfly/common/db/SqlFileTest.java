
package cn.featherfly.common.db;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.testng.annotations.Test;

import cn.featherfly.common.db.SqlFile.IncludeExistPolicy;
import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * SqlFileTest.
 *
 * @author zhongj
 */
public class SqlFileTest {
    @Test
    public void testInclude() throws IOException {
        SqlFile sqlFile = SqlFile.read(new File(ClassLoaderUtils.getResource("executor_include.sql").getFile()),
                StandardCharsets.UTF_8);
        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }

        sqlFile.write(new File("executor_include_merged.sql"));
    }

    @Test
    public void testInclude2() throws IOException {
        SqlFile sqlFile = SqlFile.read(
                new File(ClassLoaderUtils.getResource("executor_sql/executor_include.sql").getFile()),
                StandardCharsets.UTF_8);

        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }
    }

    @Test
    public void testIncludeExistPolicyIgnore() throws IOException {
        SqlFile sqlFile = SqlFile.read(
                new File(ClassLoaderUtils.getResource("executor_sql/executor_include2.sql").getFile()),
                StandardCharsets.UTF_8);

        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }

        sqlFile.write(new File("executor_include2_ignore_all.sql"));
    }

    @Test
    public void testIncludeExistPolicyInclude() throws IOException {
        SqlFile sqlFile = SqlFile.read(
                new File(ClassLoaderUtils.getResource("executor_sql/executor_include2.sql").getFile()),
                StandardCharsets.UTF_8, IncludeExistPolicy.INCLUDE);

        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }

        sqlFile.write(new File("executor_include2_ignore_include.sql"));
    }

    @Test(expectedExceptions = JdbcException.class)
    public void testIncludeExistPolicyException() throws IOException {
        SqlFile sqlFile = SqlFile.read(
                new File(ClassLoaderUtils.getResource("executor_sql/executor_include2.sql").getFile()),
                StandardCharsets.UTF_8, IncludeExistPolicy.EXCEPTION);

        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }
    }

    @Test
    public void testIncludeWithJar() throws IOException {
        SqlFile sqlFile = SqlFile.read(
                new File(ClassLoaderUtils.getResource("executor_sql/executor_include_with_jar.sql").getFile()),
                StandardCharsets.UTF_8);
        System.out.println("\nsqlList:\n");
        for (String sql : sqlFile.getSqlList()) {
            System.out.println(sql);
        }

        sqlFile.write(new File("executor_include_with_jar_merged.sql"));
    }

    public static void main(String[] args) throws IOException {
        //        Iterator<URL> iter = ClassLoaderUtils.getResources("META-INF/MANIFEST.MF", false);
        //        while (iter.hasNext()) {
        //            URL url = iter.next();
        //            Manifest manifest = new Manifest();
        //            manifest.read(url.openStream());
        //            if (url.getFile().contains("attoparser-2")) {
        //                System.out.println(url.getFile());
        //                System.out.println(manifest.getMainAttributes().entrySet());
        //            }
        //        }

        System.out.println(ClassLoaderUtils.getResource("META-INF/test/user.sql"));
    }
}
