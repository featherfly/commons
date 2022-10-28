
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;

import javax.activation.MimeTypeParseException;

import cn.featherfly.common.structure.HashChainMap;

/**
 * Test.
 *
 * @author zhongj
 */
public class TestUpload {

    String uploadUrl = "http://localhost:8080/upload";

    @org.testng.annotations.Test
    public void test1() throws MimeTypeParseException {

        String result = Http.post(uploadUrl,
                new HashChainMap<String, Serializable>().putChain("key", "key2")
                        .putChain("file1", new UploadFile("filename1", "text/plain", "abcde".getBytes()))
                        .putChain("file2", new UploadFile("filename1", "text/plain", "12345".getBytes())));

        assertEquals(result, "OK");
    }
}
