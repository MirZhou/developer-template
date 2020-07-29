package cn.mir.background.management.utils.validation;

import java.util.List;

/**
 * <p>create time：2020-07-28 11:13
 *
 * @author Eros
 */
public abstract class AbstractParamValid {
    /**
     * 检测参数有效性
     *
     * @return 布尔值
     */
    public abstract List<FieldErrorMessage> checkParameters();
}
