package cn.mir.background.management.controller;

import cn.mir.background.management.dto.submit.UserSubmit;
import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>Create time: 2020/4/25 16:01</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class UserController extends BaseController {
    @PostMapping(value = "/user")
    public ResponseResult<Void> save(@Valid @RequestBody UserSubmit submitData,
                                     BindingResult bindingResult) {
        // 打印日志
        log.info("提交用户信息 param:{}", submitData);

        return new ResponseResult<>(true, "提交成功");
    }
}
