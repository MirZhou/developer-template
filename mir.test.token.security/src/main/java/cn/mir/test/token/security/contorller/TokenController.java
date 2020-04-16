package cn.mir.test.token.security.contorller;

import cn.mir.common.utilities.encryption.DesUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 生成token
 * <p>Create time: 2020/4/16 22:24</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class TokenController {
    private final RedisTemplate<String, String> redisTemplate;

    public TokenController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping(value = "/gen/token/{username}")
    public String generationToken(@RequestBody ValidationParamSubmit submit) {
        log.info("生成token param:{}", submit);

        String token = UUID.randomUUID().toString().replace("-", "");

        log.info("username:{} \t token:{}", submit.getUsername(), token);

        // 构造缓存实体对象
        RedisCacheEntity cacheEntity = new RedisCacheEntity(submit.getUsername(), submit.translateToParameter());

        // 写入redis
        this.redisTemplate.opsForValue().set(token, JSON.toJSONString(cacheEntity), 1, TimeUnit.HOURS);

        return token;
    }

    @GetMapping(value = "/validation/token")
    public String validation(@RequestHeader String token,
                             @RequestHeader String sign) {
        log.info("校验token value:{}", token);

        // 从redis中读取
        String cacheEntityJson = this.redisTemplate.opsForValue().get(token);

        if (cacheEntityJson == null || "".equals(cacheEntityJson.trim())) {
            return "无缓存数据";
        }

        RedisCacheEntity cacheEntity = JSON.parseObject(cacheEntityJson, RedisCacheEntity.class);

        log.info("cache entity:{}", cacheEntity);

        if (cacheEntity == null) {
            return "无缓存数据";
        }

        log.info("token:{} \t username:{}", token, cacheEntity.getUsername());

        // 获得解密密钥
        String encryptKey = token.substring(8, 16);

        try {
            // 获取解密后的值
            String decryptedValue = DesUtils.decrypt(sign, encryptKey);

            if ("".equals(decryptedValue.trim())) {
                throw new Exception();
            }

            ValidationParameter compareParam = JSON.parseObject(decryptedValue, ValidationParameter.class);

            boolean validationResult = cacheEntity.executionValidation(compareParam);

            return validationResult ? cacheEntity.getUsername() : "无效会话";
        } catch (Exception e) {
            log.error("des decrypt has error. sign:{} \t key:{}", sign, encryptKey, e);

            return "无效会话";
        }
    }
}
