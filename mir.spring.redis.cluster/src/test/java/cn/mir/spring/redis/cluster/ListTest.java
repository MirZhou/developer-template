package cn.mir.spring.redis.cluster;

import cn.mir.spring.redis.cluster.entity.RedisData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>create time：2020-08-21 09:35
 *
 * @author Eros
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTest {
    private static final String KEY = "eros-redis-list-demo";

    @Autowired
    private RedisTemplate<String, RedisData> redisTemplate;

    @Test
    public void test() {
        this.insert();
        this.print();
        this.delete();
        this.print();
    }

    private void insert() {
        System.out.println("================insert");
        List<RedisData> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10)
                .forEach(i -> list.add(new RedisData((long) i, "测试数据" + i)));

        this.redisTemplate.opsForList().leftPushAll(KEY, list);
    }

    private void print() {
        System.out.println("================print");
        this.redisTemplate.opsForList().range(KEY, 0, -1)
                .forEach(System.out::println);
    }

    private void delete() {
        System.out.println("================delete");
        Boolean result = this.redisTemplate.delete(KEY);

        System.out.println("================delete " + result);
    }
}
