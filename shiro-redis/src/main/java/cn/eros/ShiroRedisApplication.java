package cn.eros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动入口
 * <p>create time：2020-08-05 11:39
 *
 * @author Eros
 */
@SpringBootApplication
public class ShiroRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroRedisApplication.class, args);
    }
}
