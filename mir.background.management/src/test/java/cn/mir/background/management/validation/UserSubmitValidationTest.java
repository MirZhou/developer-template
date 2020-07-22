package cn.mir.background.management.validation;

import cn.mir.background.management.dto.submit.UserSubmit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * <p>Create time: 2020/7/19 00:56</p>
 *
 * @author 周光兵
 */
@Slf4j
public class UserSubmitValidationTest {
    @Test
    public void testValidation() {
        UserSubmit entity = new UserSubmit();
        entity.setUsername("eros");

        try(ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<UserSubmit>> cvSet = validator.validate(entity);

            cvSet.forEach(item -> {
                log.info("classname:{} property:{} error:{}",
                        item.getRootBeanClass().getName(),
                        item.getPropertyPath(),
                        item.getMessage());
            });
        }
    }
}
