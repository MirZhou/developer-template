package cn.mir.test.aop.security;

/**
 * 模拟登录用户信息
 * <p>Create time: 2019/4/9 10:25 PM</p>
 *
 * @author 周光兵
 */
public class CurrentUserHolder {
    /**
     * 用户名
     */
    private static String currentName;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public static String get() {
        return currentName;
    }

    /**
     * 更新用户信息
     *
     * @param name 用户名
     */
    public static void set(String name) {
        currentName = name;
    }
}