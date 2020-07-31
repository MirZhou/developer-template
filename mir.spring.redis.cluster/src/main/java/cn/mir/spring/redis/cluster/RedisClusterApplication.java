package cn.mir.spring.redis.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 模块启动入口
 * <p>create time：2020-07-30 13:43
 *
 * @author Eros
 */
@SpringBootApplication
public class RedisClusterApplication {
    public RedisClusterApplication(RedisTemplate<String, String> redisTemplate) {
        // 测试数据，仅保留三分钟
//        redisTemplate.opsForValue().set("redis-cluster-1", "redis-cluster-1-value",1, TimeUnit.MINUTES);
//        redisTemplate.opsForValue().set("redis-cluster-2", "redis-cluster-2-value",1, TimeUnit.MINUTES);
//        redisTemplate.opsForValue().set("redis-cluster-3", "redis-cluster-3-value",1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterApplication.class, args);
    }
}
