
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-20 18:55:20
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.exception.UnsupportedException;

/**
 * TestIndex.
 *
 * @author zhongj
 */
public class TestIndex {
    static final long MAX = Integer.MAX_VALUE * 1000L;

    int[] array5 = new int[5];

    int[] array20 = new int[20];

    int i = 0;

    @BeforeClass
    public void bc() {
        for (int i = 0; i < array5.length; i++) {
            array5[i] = i + 1;
        }
        for (int i = 0; i < array20.length; i++) {
            array20[i] = i + 1;
        }
    }

    @Test()
    public void array_5_size() {
        for (long i = 0; i < MAX; i++) {
            array5(0);
            array5(2);
            array5(4);
        }
    }

    @Test()
    public void array_20_size() {
        for (long i = 0; i < MAX; i++) {
            array20(0);
            array20(10);
            array20(19);

        }
    }

    int array5(int i) {
        return array5[i];
    }

    int array20(int i) {
        return array20[i];
    }

    @Test()
    public void switch_5_size() {
        for (long i = 0; i < MAX; i++) {
            switch5(0);
            switch5(2);
            switch5(4);

        }
    }

    @Test()
    public void switch_20_size() {
        for (long i = 0; i < MAX; i++) {
            switch20(0);
            switch20(10);
            switch20(19);
        }
    }

    int switch5(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            default:
                throw new UnsupportedException();
        }
    }

    int switch20(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            case 10:
                return 11;
            case 11:
                return 12;
            case 12:
                return 13;
            case 13:
                return 14;
            case 14:
                return 15;
            case 15:
                return 16;
            case 16:
                return 17;
            case 17:
                return 18;
            case 18:
                return 19;
            case 19:
                return 20;
            default:
                throw new UnsupportedException();
        }
    }

}
