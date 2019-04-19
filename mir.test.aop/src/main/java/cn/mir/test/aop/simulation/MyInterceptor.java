package cn.mir.test.aop.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Create time: 2019/4/17 11:53 PM</p>
 *
 * @author 周光兵
 */
public class MyInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean before() {
        this.logger.info(this.getClass().getName() + "-------before");
        return true;
    }

    @Override
    public void after() {
        this.logger.info("{}-------after", this.getClass().getName());
    }

    @Override
    public Object around(Invocation invocation) throws Throwable {
        this.logger.info("{}-------around before", this.getClass().getName());

        Object obj = invocation.proceed();

        this.logger.info("{}-------around after", this.getClass().getName());

        return null;
    }

    @Override
    public void afterReturning() {
        this.logger.info("{}-------after returning", this.getClass().getName());
    }

    @Override
    public void afterThrowing() {
        this.logger.info("{}-------after throwing", this.getClass().getName());

    }

    @Override
    public boolean useAround() {
        return true;
    }
}