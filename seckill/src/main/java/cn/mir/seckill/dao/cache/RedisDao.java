package cn.mir.seckill.dao.cache;

import cn.mir.seckill.entity.Seckill;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>Create time: 2019/4/21 11:25 AM</p>
 *
 * @author 周光兵
 */
public class RedisDao {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * jedis资源池
     */
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port, String password) {
        this.jedisPool = new JedisPool(new GenericObjectPoolConfig(), ip, port, 2000, password);
    }

    /**
     * 定义Seckill对象的schema
     */
    private RuntimeSchema<Seckill> seckillRuntimeSchema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        try {

            try (Jedis jedis = this.jedisPool.getResource()) {
                String key = "seckill:" + seckillId;

                // 并没有实现内部序列化操作
                // 采用自定义序列化

                // 从缓存读取
                byte[] bytes = jedis.get(key.getBytes());

                if (bytes != null) {
                    // 创建空对象
                    Seckill seckill = seckillRuntimeSchema.newMessage();
                    // 反序列化
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, seckillRuntimeSchema);

                    return seckill;
                }
            }

        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    public String putSeckill(Seckill seckill) {
        try {

            try (Jedis jedis = this.jedisPool.getResource()) {
                String key = "seckill:" + seckill.getSeckillId();

                // 序列化
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, seckillRuntimeSchema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

                // 缓存时间
                int timeout = 60 * 60;

                // 写入缓存数据，并返回缓存结果
                return jedis.setex(key.getBytes(), timeout, bytes);
            }

        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }
}
