package cn.mir.background.management.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.mir.background.management.utils.ShiroRealm;

/**
 * ShiroConfiguration
 * 
 * @author Eros
 */
@Configuration
public class ShiroConfiguration {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 配置URL规则和访问权限
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        this.logger.info("Shiro 拦截器工厂类注入成功");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录地址。如果不设置，默认访问工程目录下的"/login.jsp"页面，或者"/login"的映射
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置无权限时的跳转地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new HashMap<>(16);
        // Web引用的CSS、JS文件放行
        filterChainDefinitionMap.put("/webjars/**", "anon");
        // 开放接口："/user/login"
        filterChainDefinitionMap.put("/login", "anon");
        // 开发者相关接口全部放行，由WebSecurity接管用户验证
        filterChainDefinitionMap.put("/developer/**", "anon");
        // 所有接口都设置拦截。注：这行代码必须放在最后，否则会导致所有url都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        // 将拦截器规则注入到配置中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 注入SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        this.logger.info("注入SecurityManager");

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置Realm
        securityManager.setRealm(this.customShiroReaml());

        return securityManager;
    }

    /**
     * 自定义身份认证ShiroRealm
     * 
     * <p>
     * 必须通过@Bean注解注入此类，否则会影响ShiroRealm类中其它类的依赖注入
     * </p>
     */
    @Bean
    public ShiroRealm customShiroReaml() {
        return new ShiroRealm();
    }
}