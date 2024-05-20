
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 20:15:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * TestBase.
 *
 * @author zhongj
 */
public class TestBase {
    int total = 100000000;

    @BeforeClass
    @Parameters({ "total" })
    public void init(@Optional("100000000") int total) throws IOException {
        this.total = total;
        System.err.println("loop times " + total);
    }
}
