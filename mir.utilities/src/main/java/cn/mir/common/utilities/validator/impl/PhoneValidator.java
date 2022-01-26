package cn.mir.common.utilities.validator.impl;

import cn.mir.common.utilities.validator.Phone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 自定义验证：验证手机号码
 *
 * @author Eros
 * @date 2022/1/26 11:04
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final Pattern PATTERN = Pattern.compile("^1[0-9]{10}$");

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value) &&
            PATTERN.matcher(value).matches();
    }
}
