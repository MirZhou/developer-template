package cn.mir.test.aop.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Create time: 2019/4/17 11:50 PM</p>
 *
 * @author 周光兵
 */
public class HelloServiceImpl implements HelloService {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sayHello(String name) {
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("parameter is null!!!");
        }

        this.logger.info("Hello: {}", name);
    }
}
