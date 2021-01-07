package cn.mir.background.management.aspect;

import cn.mir.background.management.utils.validation.AbstractParamValid;
import cn.mir.background.management.utils.validation.FieldErrorMessage;
import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 自定义参数校验
 *
 * @author 周光兵
 * @date 2021/01/07 16:18
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class ParameterValidAspect {

    @Around(value = "execution(* cn.mir.background.management.controller..*.*(..))")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("AOP 拦截：对请求参数进行自定义校验");

        Object[] parameters = joinPoint.getArgs();

        if (parameters.length == 0) {
            log.info("方法无参数");
            return joinPoint.proceed();
        }

        // 错误信息列表
        List<FieldErrorMessage> errorMessages = new ArrayList<>();

        for (Object param : parameters) {
            if (param instanceof AbstractParamValid) {
                // 存在自定义参数校验方法
                AbstractParamValid paramValid = (AbstractParamValid) param;

                // 获取自定义校验结果
                errorMessages = Optional.ofNullable(paramValid.checkParameters()).orElse(Collections.emptyList());
            }
        }

        if (!errorMessages.isEmpty()) {
            // 打印所有错误信息
            errorMessages.forEach(error -> log.error("{}", error));

            // 返回第一个错误
            return new ResponseResult<>(false, errorMessages.get(0).getErrorMessage());
        }

        return joinPoint.proceed();
    }
}
