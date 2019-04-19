package cn.mir.test.aop.simulation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Create time: 2019/4/18 12:05 AM</p>
 *
 * @author 周光兵
 */
public class ProxyBean implements InvocationHandler {
    private Object target = null;
    private Interceptor interceptor = null;

    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        // 保存代理对象
        proxyBean.target = target;
        // 保存拦截器
        proxyBean.interceptor = interceptor;

        // 生成代理对象，并返回
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 异常标识
        boolean exceptionFlag = false;

        Invocation invocation = new Invocation(this.target, method, args);

        Object returnObj = null;
        try {
            if (this.interceptor.before()) {
                returnObj = this.interceptor.around(invocation);
            } else {
                returnObj = method.invoke(this.target, args);
            }

        } catch (Exception ex) {
            // 产生异常
            exceptionFlag = true;
        }

        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
        }

        return returnObj;
    }
}
