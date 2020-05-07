package cn.mir.background.management.controller;

import cn.mir.common.utilities.error.ParamErrorException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller基类
 * <p>Create time: 2019-09-04 10:33</p>
 *
 * @author 周光兵
 */
@Slf4j
public class BaseController {
    /**
     * 检测参数
     *
     * @param bindingResult 参数绑定结果
     */
    protected void checkParameter(BindingResult bindingResult) {
        // 校验提交的数据
        if (bindingResult.hasErrors()) {
            if (bindingResult.getTarget() == null) {
                throw new ParamErrorException("参数读取异常");
            }

            // 存放错误信息列表
            List<FieldErrorMessage> errorMessages = new ArrayList<>();

            // 获取要验证类的所有字段
            Field[] arrayField = bindingResult.getTarget().getClass().getDeclaredFields();

            // 遍历字段
            for (Field field : arrayField) {
                // 获取字段名
                String fieldName = field.getName();

                // 根据字段名获取错误信息
                FieldError fieldError = bindingResult.getFieldError(fieldName);

                if (fieldError == null) {
                    continue;
                }

                // 添加到错误信息列表中
                errorMessages.add(new FieldErrorMessage(fieldName, fieldError.getDefaultMessage()));
            }

            // 参数校验失败，返回错误信息
            String message = "数据异常";

            if (errorMessages.size() > 0) {

                message = errorMessages.get(0).getErrorMessage();

                // 获取所有错误信息
                String allErrorMessageStr = errorMessages.stream()
                        .map(FieldErrorMessage::getErrorMessage)
                        .collect(Collectors.joining(","));

                log.error("参数异常。all errors message：{}", allErrorMessageStr);
            }

            throw new ParamErrorException("提交异常。错误：" + message);
        }
    }

    @Data
    private static class FieldErrorMessage {
        private String fieldName;
        private String errorMessage;

        private FieldErrorMessage(String fieldName, String errorMessage) {
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }
    }

}
