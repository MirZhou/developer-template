package cn.eros.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义Session管理
 * <p>从请求头中传递token，以此来维护用户会话连接，达到前后端分离的目的
 * <p>create time：2020-08-05 15:10
 *
 * @author Eros
 */
@Slf4j
public class SessionConfig extends DefaultWebSessionManager {
    /**
     * 请求头token的参数名
     */
    private static final String AUTHORIZATION = "token";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (StringUtils.isNotBlank(token)) {
            // 请求头中有 token 则其值为sessionId
            log.info("请求头中有 token 则其值为sessionId value:{}", token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return token;
        }

        return null;
    }
}
