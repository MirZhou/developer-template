package cn.mir.test.aop.service;

import cn.mir.test.aop.security.CurrentUserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 服务：权限检测
 * <p>Create time: 2019/4/9 10:34 PM</p>
 *
 * @author 周光兵
 */
@Service
public class AuthService {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * 定义管理员用户名
     */
    private static final String ADMIN_NAME = "admin";

    /**
     * 检测权限
     */
    public void checkAccess() {
        // 获取当前用户信息
        String currentUser = CurrentUserHolder.get();

        this.logger.info("check access user:" + currentUser);

        // 执行检测
        if (!ADMIN_NAME.equalsIgnoreCase(CurrentUserHolder.get())) {
            // 记录警告日志
            this.logger.warn("Operation not allow");

            // 检测失败，抛出异常
            throw new RuntimeException("Operation not allow.");
        }

        this.logger.info("check is passed. user:" + currentUser);
    }
}
