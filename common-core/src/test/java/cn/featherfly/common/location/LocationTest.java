
/*
 * All rights Reserved, Designed By zhongj
 * @Title: LocationTest.java
 * @Package cn.featherfly.common.location
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-07-07 11:37:07
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.location;

import org.testng.annotations.Test;

/**
 * LocationTest.
 *
 * @author zhongj
 */
public class LocationTest {

    @Test
    public void test1() {
        LocationPoint p = new LocationPoint(30.663463, 104.072233);

        RectangleRange r = LocationUtils.getRectanglePoint(p, 1000);

        System.out.println(r.getLeftTop());
        System.out.println(r.getRightTop());
        System.out.println(r.getLeftBottom());
        System.out.println(r.getRightBottom());

    }
}
