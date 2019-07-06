package cn.mir.test.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 * <p>Create time: 2019-05-02 20:03</p>
 *
 * @author 周光兵
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        this.logger.info("安全项配置");

        http.authorizeRequests()
                // 验证通过后可访问
                .antMatchers("/user/*").authenticated()
                // 其它请求均可访问
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
    }
}
