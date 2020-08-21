package cn.mir.spring.redis.cluster.config;

import cn.mir.spring.redis.cluster.entity.RedisData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置
 * <p>Create time: 2020-06-09 16:06</p>
 *
 * @author Eros
 **/
@Configuration
public class RedisConfiguration {
    /**
     * redisTemplate
     */
    private static final StringRedisSerializer STRING_REDIS_SERIALIZER = new StringRedisSerializer();

    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * 解决redis插入中文乱码
     *
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> listObjectOperations(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(STRING_REDIS_SERIALIZER);

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    /**
     * 楼层redis
     */
    @Bean
    public RedisTemplate<String, RedisData> listRedisDataOperations(RedisConnectionFactory factory) {
        RedisTemplate<String, RedisData> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(STRING_REDIS_SERIALIZER);

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

//        // 配置默认的序列化器
//        redisTemplate.setDefaultSerializer(this.getGenericJackson2JsonRedisSerializer());
//
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // 设置 Key 的默认序列化机制
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        return redisTemplate;
    }
}
