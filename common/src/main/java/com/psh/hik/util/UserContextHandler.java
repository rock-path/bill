package com.psh.hik.util;

import com.psh.hik.common.UserInfo;

/**
 * @author softstone71.hik.
 * @DATE 2021/9/24.
 * @time 11:06.
 * * 用户工具类
 */
public class UserContextHandler {

    private static ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    public static void setUser(UserInfo user) {
        userHolder.set(user);
    }

    public static UserInfo getCurrentUser() {
        return userHolder.get();
    }

    public static void cleanUser() {
        userHolder.remove();
    }

    public static void reflash() {
        userHolder = new ThreadLocal<>();
    }
}
