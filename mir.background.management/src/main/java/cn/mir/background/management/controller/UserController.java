package cn.mir.background.management.controller;

import cn.mir.background.management.dto.submit.UserSubmit;
import cn.mir.common.utilities.ResponseResult;
import cn.mir.common.utilities.validator.groups.Create;
import cn.mir.common.utilities.validator.groups.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Create time: 2020/4/25 16:01</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class UserController {
    @PostMapping(value = "/user")
    public ResponseResult<Void> add(@Validated(Create.class) @RequestBody UserSubmit submitData) {
        // 打印日志
        log.info("提交用户信息 param:{}", submitData);

        return new ResponseResult<>(true, "提交成功");
    }

    @PutMapping(value = "/user")
    public ResponseResult<Void> update(@Validated(Update.class) @RequestBody UserSubmit submitData) {
        // 打印日志
        log.info("提交用户信息 param:{}", submitData);

        return new ResponseResult<>(true, "提交成功");
    }
}
