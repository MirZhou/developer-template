package cn.mir.test.aop.security;

import cn.mir.test.aop.domain.Product;
import cn.mir.test.aop.service.AuthService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AOP实例：权限检测
 * <p>Create time: 2019/4/9 10:27 PM</p>
 *
 * @author 周光兵
 */
@Aspect
@Component
public class SecurityAspect {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    /**
     * 权限检测服务
     */
    private final AuthService authService;

    /**
     * 构造方法注入对象
     *
     * @param authService 权限检测服务
     */
    @Autowired
    public SecurityAspect(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 定义要检测的目标
     */
    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {
    }

    /**
     * 执行具体的检测方法
     */
    @Before("adminOnly()")
    public void adminOnlyCheck() {
        this.authService.checkAccess();
    }

    @AfterReturning(value = "execution(* cn.mir.test.aop.service.ProductService.get())", returning = "product")
    public void getProduct(Product product) {
        this.logger.info("AOP 修改前：" + product);

        product.setId(product.getId() - 1);
        product.setName("药品");

        this.logger.info("AOP 修改后：" + product);
    }
}