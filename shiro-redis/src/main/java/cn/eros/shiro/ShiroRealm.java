package cn.eros.shiro;

import cn.eros.dto.submit.LoginSubmit;
import com.sun.deploy.net.proxy.pac.PACFunctionsImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义身份认证ShiroRealm
 * <p>create time：2020-08-05 14:22
 *
 * @author Eros
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private static final String SALT = "bdlh";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("获取用户授权");

        // principals.getPrimaryPrincipal()

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("用户认证");

        // 用户名
        String username = (String)token.getPrincipal();

        if (!USERNAME.equals(username)) {
            return null;
        }

        LoginSubmit entity = new LoginSubmit();
        entity.setUsername(username);
        entity.setPassword("dfkslajflkdsjalfjdsakl");

        // 通过用户名查找用户信息

        return new SimpleAuthenticationInfo(
                entity,
                PASSWORD,
                ByteSource.Util.bytes(SALT),
                getName()
        );
    }
}
