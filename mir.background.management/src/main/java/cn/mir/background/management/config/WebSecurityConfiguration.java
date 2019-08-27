package cn.mir.background.management.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebConfiguration
 * 
 * <p>
 * Create time: 2019/8/18 23:50
 * </p>
 * 
 * @author Eros
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 密码编译器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 使用内存存储
        auth.inMemoryAuthentication()
                // 设置密码编译器
                .passwordEncoder(passwordEncoder)
                // 注册用户admin
                .withUser("eros")
                // 密码为abc
                .password(passwordEncoder.encode("abc"))
                // 赋予USER和ADMIN的角色权限
                .roles("ADMIN", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        this.logger.info("安全项配置");

        http.csrf().disable()
                // 签名登录后
                .authorizeRequests()
                // 要求登录用户拥有ADMIN角色
                .antMatchers("/developer/**").hasRole("ADMIN")
                // 没有配置权限的其它请求允许匿名访问
                .and().anonymous()
                // 使用默认登录页面
                .and().formLogin().loginPage("/developer/login").permitAll()
                // 启动HTTP基础验证
                .and().httpBasic();
    }
}