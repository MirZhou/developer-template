package cn.eros.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * <p>create time：2020-08-06 15:54
 *
 * @author Eros
 */
@Slf4j
@Component
public class AuthSessionDao extends EnterpriseCacheSessionDAO {
    /**
     * 会话缓存key的前缀
     */
    private final static String KEY_PREFIX = "eros_test";

    private final RedisTemplate<String, String> redisTemplate;

    public AuthSessionDao(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        this.writeToRedis(session);

        log.info("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = redisTemplate.opsForValue().get(this.generateKey(sessionId));

        log.info("doReadSession >>>>> sessionId={}", sessionId);

        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }

        // 更新session的最后一次访问时间
        this.writeToRedis(session);

        log.info("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        Serializable sessionId = session.getId();

        // 删除session
        redisTemplate.delete(this.generateKey(sessionId));

        log.info("doDelete >>>>> sessionId={}", sessionId);
    }

    /**
     * 生成Key
     *
     * @param sessionId sessionId
     * @return key
     */
    private String generateKey(Serializable sessionId) {
        return KEY_PREFIX + "_" + sessionId;
    }

    /**
     * session写入到redis中
     *
     * @param session session对象
     */
    private void writeToRedis(Session session) {
        String key = this.generateKey(session.getId());
        String value = SerializableUtil.serialize(session);

        redisTemplate.opsForValue().set(key, value, (int) session.getTimeout() / 1000, TimeUnit.SECONDS);
    }
}
