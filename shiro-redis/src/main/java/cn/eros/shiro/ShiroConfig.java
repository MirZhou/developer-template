package cn.eros.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Shiro配置
 * <p>create time：2020-08-05 14:02
 *
 * @author Eros
 */
@Slf4j
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroRealm userRealm() {
        ShiroRealm realm = new ShiroRealm();

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 指定加密算法
        matcher.setHashAlgorithmName("MD5");
        // 指定加密次数
        matcher.setHashIterations(3);
        // 指定这个就不会报错
        matcher.setStoredCredentialsHexEncoded(true);

        realm.setCredentialsMatcher(matcher);

        return new ShiroRealm();
    }

    /**
     * 配置SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AuthSessionDao authSessionDao) {
        SessionConfig sessionConfig = new SessionConfig();
        sessionConfig.setSessionDAO(authSessionDao);
        sessionConfig.setGlobalSessionTimeout(TimeUnit.MINUTES.toMillis(2L));

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(this.userRealm());
        // 设置sessionManager
        securityManager.setSessionManager(sessionConfig);

        return securityManager;
    }

    /**
     * 配置URL规则和访问权限
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        log.info("Shiro 拦截器工厂类注入成功");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录拦截器
        shiroFilterFactoryBean.getFilters().put("authc", new ShiroLoginFilter());

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new HashMap<>(4);
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

}
