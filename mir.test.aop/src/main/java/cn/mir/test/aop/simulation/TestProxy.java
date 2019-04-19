package cn.mir.test.aop.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Create time: 2019/4/18 12:04 AM</p>
 *
 * @author 周光兵
 */
public class TestProxy {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());

        proxy.sayHello("mir zhou");
        System.out.println("\n*************************name is null*************************\n");
        proxy.sayHello(null);
    }
}
