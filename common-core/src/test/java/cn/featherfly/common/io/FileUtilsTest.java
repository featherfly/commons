package cn.featherfly.common.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * FileUtilsTest.
 *
 * @author zhongj
 */
public class FileUtilsTest {

    @org.testng.annotations.Test
    public void testWatch() throws IOException, InterruptedException {
        File dir = new File(new File("bin/watch").getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file1 = new File("./bin/watch/1.txt");
        File file2 = new File("./bin/watch/a/2.txt");
        //        if (file1.exists()) {
        //            FileUtils.deleteFile(file1);
        //        }
        FileUtils.makeDirectory(file2);
        FileUtils.watchAll(dir, (event, watchDir) -> {
            File wf = new File(watchDir.toFile().getAbsolutePath() + "/" + event.context().toFile().getName());
            System.out.println(event.kind() + "    " + event.context() + "     " + wf.getAbsolutePath() + "    "
                    + wf.exists() + "    " + wf.isFile());
            System.out.println(watchDir.toFile().getAbsolutePath());
        });

        try (OutputStream os = new FileOutputStream(file1);
                OutputStreamWriter w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            w.write("123");
            Thread.sleep(500);
            w.write("abc");
            Thread.sleep(500);
            file1.delete();
        }
        try (OutputStream os = new FileOutputStream(file2);
                OutputStreamWriter w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            w.write("123");
            Thread.sleep(500);
            w.write("abc");
            Thread.sleep(500);
            file2.delete();
        }
        Thread.sleep(500);

        file1.delete();
        file2.delete();
        FileUtils.deleteDir(dir);
    }
}
