package cn.featherfly.common.lang.debug;

import org.testng.annotations.Test;

import cn.featherfly.common.structure.ChainMapImpl;

/**
 * The Class TableMessageTest.
 *
 * @author zhongj
 */
public class TableMessageTest {

    @Test
    void test1() {
        TableMessage message = new TableMessage();
        message.addColumn("name", "age", "password");

        message.addRow(new ChainMapImpl<String, Object>().putChain("name", "yufei").putChain("age", 11)
                .putChain("password", "123456"))
                .addRow(new ChainMapImpl<String, Object>().putChain("name", "yi").putChain("age", 22)
                        .putChain("password", "111111"))
                .addRow(new ChainMapImpl<String, Object>().putChain("name", "featherfly").putChain("age", 33)
                        .putChain("password", "000000"));
        System.out.println(message.toString());
    }

}