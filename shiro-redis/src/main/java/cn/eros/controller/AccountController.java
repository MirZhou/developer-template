package cn.eros.controller;

import cn.eros.dto.submit.LoginSubmit;
import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>create time：2020-08-05 18:56
 *
 * @author Eros
 */
@Slf4j
@RestController
public class AccountController {
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody LoginSubmit submitData) {
        Subject subject;
        try {
            // 从SecurityUtils中创建一个Subject
            subject = SecurityUtils.getSubject();

            // 认证提交前准备Token
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(submitData.getUsername(),
                    submitData.getPassword());

            // 执行登录认证
            subject.login(usernamePasswordToken);

        } catch (UnknownAccountException e) {
            log.error("未找到用户【{}】", submitData.getUsername());

            return new ResponseResult<>(false, "用户名或密码输入不正确");

        } catch (IncorrectCredentialsException e) {
            log.error("用户【{}】密码输入错误", submitData.getUsername());

            return new ResponseResult<>(false, "用户名或密码输入不正确");

        } catch (Exception e) {
            log.error("登录请求异常。Message：" + e.getMessage(), e);

            return new ResponseResult<>(false, "登录请求异常。Message:" + e.getMessage());
        }

        return new ResponseResult<>(true, "登录成功", subject.getSession().getId().toString());
    }

    @GetMapping("/current/username")
    public ResponseResult<String> getCurrentUsername() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();

        return new ResponseResult<>(true, "调用成功", username);
    }

    @GetMapping("/logout")
    public ResponseResult<Void> logout() {
        Subject subject = SecurityUtils.getSubject();

        log.info("用户注销登录 username:{}", subject.getPrincipal());

        subject.logout();

        return new ResponseResult<>(true, "用户已注销登录");
    }
}
