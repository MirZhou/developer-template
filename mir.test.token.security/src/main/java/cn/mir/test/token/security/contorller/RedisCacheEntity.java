package cn.mir.test.token.security.contorller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Redis缓存实体
 * <p>Create time: 2020/4/17 00:48</p>
 *
 * @author 周光兵
 */
@Slf4j
@Data
public class RedisCacheEntity implements Serializable {
    private static final long serialVersionUID = -5705411369223481321L;

    /**
     * 缓存的用户信息
     */
    private String username;
    /**
     * 校验参数
     */
    private ValidationParameter validation;

    public RedisCacheEntity() {
    }

    public RedisCacheEntity(String username, ValidationParameter validation) {
        this.username = username;
        this.validation = validation;
    }

    /**
     * 执行校验
     *
     * @param compareParam 要比较的对象
     * @return 校验结果（布尔值）。true：校验通过；false：校验失败
     */
    public boolean executionValidation(ValidationParameter compareParam) {
        if (!this.validation.getDeviceId().equals(compareParam.getDeviceId())) {
            log.warn("Device id doesn't match.");
            return false;
        }

        if (!this.validation.getDeviceName().equals(compareParam.getDeviceName())) {
            log.warn("Device name doesn't match.");
            return false;
        }

        if (!this.validation.getDeviceType().equals(compareParam.getDeviceType())) {
            log.warn("Device type doesn't match.");
            return false;
        }

        // 获取客户端提交的时间戳
        Long timestamp = compareParam.getTimestamp();

        if (timestamp == null) {
            log.warn("Invalid timestamp.");
            return false;
        }

        // 签名有效期：5分钟
        long timeoutValue = 5 * 60 * 1000;
        // 计算请求时间差，取绝对值
        long duration = Math.abs(System.currentTimeMillis() - timestamp);

        if (duration > timeoutValue) {
            // 时间戳无效或已过期
            log.warn("Timestamp is invalid or has expired.");

            return false;
        }

        // 请求时间差的绝对值在5分钟之内有效
        return true;
    }
}
