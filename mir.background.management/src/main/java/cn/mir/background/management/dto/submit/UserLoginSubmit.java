package cn.mir.background.management.dto.submit;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 用户登录请求提交数据
 * 
 * @author Eros
 */
@Data
public class UserLoginSubmit {
    @NotBlank(message = "请填写用户名")
    private String username;
    @NotBlank(message = "请填写密码")
    private String password;
}