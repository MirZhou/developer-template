package cn.mir.background.management.utils;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.mir.background.management.entity.User;
import cn.mir.background.management.service.UserService;

/**
 * ShiroRealm
 * 
 * @author Eros
 */
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 用户Service
     */
    @Resource
    private UserService userService;

    /**
     * 用于获取用户的权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // todo 通过用户信息查询用户角色及权限

        // 用于存放角色，及角色对应的权限列表
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 获取当前登录用户
        //User user = (User)principals.getPrimaryPrincipal();

        // todo 以下是模拟代码
        // 添加角色：ADMIN
        authorizationInfo.addRole("ADMIN");
        // 添加角色【ADMIN】用户的权限
        authorizationInfo.addStringPermission("user:select");
        authorizationInfo.addStringPermission("user:add");

        return authorizationInfo;
    }

    /**
     * 用于验证用户输入的用户名和密码是否正确
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String username = (String)token.getPrincipal();

        // 通过用户名查找用户信息
        User user = this.userService.getByUsername(username);

        if (user == null) {
            return null;
        }

        // 创建用户信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
            user,
            user.getPassword(),
            getName()
        );

		return authenticationInfo;
	}    
}