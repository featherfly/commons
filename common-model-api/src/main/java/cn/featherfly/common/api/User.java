
/*
 * All rights Reserved, Designed By zhongj
 * @Title: User.java
 * @Package cn.featherfly.common.api
 * @Description: User Model
 * @author: zhongj
 * @date: 2021-11-17 15:27:17
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.api;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * User.
 *
 * @author zhongj
 */
public interface User {

    Serializable getId();

    String getUsername();

    String getMobile();

    String getEmail();

    String getPassword();

    Gender getGender();

    String getName();

    String getNickname();

    Date getRegisterTime();

    String getToken();

    LocalDate getBirthday();

    String getAvatar();

    /**
     * The Enum Gender.
     *
     * @author zhongj
     */
    public enum Gender {

        /** The male. */
        MALE,
        /** The female. */
        FEMALE
    }
}
