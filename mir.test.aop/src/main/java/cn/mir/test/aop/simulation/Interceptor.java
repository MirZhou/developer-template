package cn.mir.test.aop.simulation;

/**
 * <p>Create time: 2019/4/17 11:54 PM</p>
 *
 * @author 周光兵
 */
public interface Interceptor {
    /**
     * 事前方法
     *
     * @return 标识是否继续执行后续代码
     */
    boolean before();

    /**
     * 事后方法
     */
    void after();

    /**
     * 取代原有事件方法
     *
     * @param invocation -- 回调参数，可以通过它的proceed方法，回调原有事件
     * @return 原有事件返回对象
     */
    Object around(Invocation invocation) throws Throwable;

    /**
     * 事后返回方法。事件没有发生异常时执行
     */
    void afterReturning();

    /**
     * 事后异常方法。当事件发生异常后执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法取代原有方法
     *
     * @return 布尔值
     */
    boolean useAround();
}
