package cn.mir.background.management.controller;

import cn.mir.background.management.dto.submit.UserLoginSubmit;
import cn.mir.common.utilities.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Controller: 账户登录
 *
 * @author Eros
 */
@Controller
public class AccountController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 视图：登录
     */
    @GetMapping(value = "/login")
    public String login() {
        this.logger.info("访问登录页");

        return "login";
    }

    /**
     * 登录请求处理
     *
     * @param submitData    请求提交数据
     * @param bindingResult 绑定验证结果
     * @return 返回处理结果
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult<Void> loginPost(@Valid UserLoginSubmit submitData, BindingResult bindingResult) {
        // 校验参数
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError() != null ?
                    bindingResult.getFieldError().getDefaultMessage()
                    : "提交数据异常";

            return new ResponseResult<>(false, message);
        }

        // 创建返回结果
        ResponseResult<Void> result = new ResponseResult<>(true, "登录成功");

        try {
            // 从SecurityUtils中创建一个Subject
            Subject subject = SecurityUtils.getSubject();

            // 认证提交前准备Token
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(submitData.getUsername(),
                    submitData.getPassword());

            // 执行登录认证
            subject.login(usernamePasswordToken);

        } catch (UnknownAccountException e) {
            this.logger.error("未找到用户【{}】", submitData.getUsername());

            result.setSuccess(false);
            result.setMessage("用户名或密码输入不正确");

        } catch (IncorrectCredentialsException e) {
            this.logger.error("用户【{}】密码输入错误", submitData.getUsername());

            result.setSuccess(false);
            result.setMessage("用户名或密码输入不正确");

        } catch (Exception e) {
            this.logger.error("登录请求异常。Message：" + e.getMessage(), e);

            result.setSuccess(false);
            result.setMessage("登录请求异常。Message:" + e.getMessage());
        }

        // 返回对象
        return result;
    }
}