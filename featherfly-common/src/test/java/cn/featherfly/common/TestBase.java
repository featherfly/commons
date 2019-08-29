
package cn.featherfly.common;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeSuite;

import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * <p>
 * TestBase
 * </p>
 * <p>
 * 2019-08-29
 * </p>
 * 
 * @author zhongj
 */
public class TestBase {

    @BeforeSuite
    public void before() {
        DOMConfigurator.configure(ClassLoaderUtils.getResource("log4j.xml", TestBase.class));
    }
}
