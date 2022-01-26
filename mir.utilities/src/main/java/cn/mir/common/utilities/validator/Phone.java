package cn.mir.common.utilities.validator;

import cn.mir.common.utilities.validator.impl.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

/**
 * @author Eros
 * @date 2022/1/26 11:03
 */
@Constraint(validatedBy = {PhoneValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank(message = "请填写手机号码")
public @interface Phone {
    String message() default "请填写正确的手机号码";

    // 下面这两个属性必须添加
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
