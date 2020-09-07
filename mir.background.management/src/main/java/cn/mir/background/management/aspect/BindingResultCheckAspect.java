package cn.mir.background.management.aspect;

import cn.mir.background.management.utils.validation.AbstractParamValid;
import cn.mir.background.management.utils.validation.FieldErrorMessage;
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
import java.util.*;

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

    @Around(value = "execution(* cn.mir.background.management.controller..*.*(..)) && args(.., bindingResult)")
    public Object bindingResultCheck(ProceedingJoinPoint joinPoint, BindingResult bindingResult) throws Throwable {
        log.info("AOP 拦截：对DELETE、POST、PUT请求参数进行校验");

        // 错误信息列表
        List<FieldErrorMessage> errorMessages = null;

        if (bindingResult.hasErrors()) {
            // 错误信息列表
            errorMessages = this.getFieldErrorMessages(bindingResult);
        } else if (bindingResult.getTarget() instanceof AbstractParamValid) {
            // 存在自定义参数校验方法
            AbstractParamValid paramValid = (AbstractParamValid) bindingResult.getTarget();

            // 获取自定义校验结果
            errorMessages = Optional.ofNullable(paramValid.checkParameters()).orElse(Collections.emptyList());
        }

        if (errorMessages != null && errorMessages.size() > 0) {
            // 打印所有错误信息
            errorMessages.stream()
                    .map(item -> "field:" + item.getFieldName() + " \t message:" + item.getErrorMessage())
                    .forEach(System.out::println);

            // 返回第一个错误
            return new ResponseResult<>(false, errorMessages.get(0).getErrorMessage());
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
}
