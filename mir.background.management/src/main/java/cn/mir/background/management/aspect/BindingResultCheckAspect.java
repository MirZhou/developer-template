package cn.mir.background.management.aspect;

import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * AOP：对DELETE、POST、PUT请求绑定参数，进行参数校验
 * <p>Create time: 2020/5/20 22:21</p>
 *
 * @author 周光兵
 */
@Slf4j
@Aspect
@Component
public class BindingResultCheckAspect {

    /**
     * Delete mapping
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMappingPointcut() {
    }

    /**
     * Post mapping
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut() {
    }

    /**
     * Put mapping
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMappingPointcut() {
    }

    @Around(value = "deleteMappingPointcut() || postMappingPointcut() || putMappingPointcut()")
    public Object bindingResultCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("AOP 拦截：对DELETE、POST、PUT请求参数进行校验");

        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 标识是否包含错误
        boolean hasError = false;
        // 错误信息
        String message = "";

        // 遍历参数
        for (Object arg : args) {
            // 判断参数类型是否为: org.springframework.validation.BindingResult
            boolean argIsBindingResult = arg instanceof BindingResult;

            if (!argIsBindingResult) {
                // 参数类型不匹配
                continue;
            }

            // 类型匹配，强制类型转换
            BindingResult bindingResult = (BindingResult) arg;
            if (bindingResult.hasErrors()) {
                hasError = true;

                // 错误信息列表
                List<FieldErrorMessage> errorMessages = this.getFieldErrorMessages(bindingResult);

                // 获取第一个错误
                message = "提交异常。错误：" + errorMessages.get(0).getErrorMessage();

                // 获取所有错误信息
                String allErrorMessageStr = errorMessages.stream()
                        .map(FieldErrorMessage::getErrorMessage)
                        .collect(Collectors.joining(","));

                log.error("参数异常。all errors message：{} \t params:{}",
                        allErrorMessageStr, bindingResult.getTarget());

                /*
                 * 退出for循环
                 * 因BindingResult参数，在方法中只有一个
                 */
                break;
            }
        }

        if (hasError) {
            return new ResponseResult<>(false, message);
        }

        return joinPoint.proceed();
    }

    /**
     * 从错误绑定结果中，获取所有错误字段及其错误信息
     *
     * @param bindingResult 错误绑定结果
     * @return 错误信息列表
     */
    private List<FieldErrorMessage> getFieldErrorMessages(BindingResult bindingResult) {
        // 存放错误信息列表
        List<FieldErrorMessage> errorMessages = new ArrayList<>();

        // 获取要验证类的所有字段
        Field[] arrayField = Objects.requireNonNull(bindingResult.getTarget())
                .getClass()
                .getDeclaredFields();

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

        return errorMessages;
    }

    /**
     * 自定义内部类：字段错误信息
     */
    private static class FieldErrorMessage {
        /**
         * 字段名
         */
        private String fieldName;
        /**
         * 错误信息
         */
        private String errorMessage;

        private FieldErrorMessage(String fieldName, String errorMessage) {
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
