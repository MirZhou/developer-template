package cn.mir.test.aop.simulation;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>Create time: 2019/4/18 12:16 AM</p>
 *
 * @author 周光兵
 */
@Data
public class Invocation {
    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        // 反射方法
        return method.invoke(target, params);
    }
}
