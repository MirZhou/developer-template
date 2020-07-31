package cn.mir.spring.redis.cluster;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * <p>create time：2020-07-31 15:58
 *
 * @author Eros
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisReadTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void read() {
        String name = "eros";

        // 写入测试数据
        this.redisTemplate.opsForValue().set("name", name, 30, TimeUnit.SECONDS);

        // 测试读取
        String dbValue = this.redisTemplate.opsForValue().get("name");

        Assert.assertEquals(name, dbValue);
    }
}
