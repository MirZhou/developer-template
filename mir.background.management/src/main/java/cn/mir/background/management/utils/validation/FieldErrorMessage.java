package cn.mir.background.management.utils.validation;

import lombok.Data;

/**
 * 字段错误信息
 * <p>create time：2020-07-29 09:30
 *
 * @author Eros
 */
@Data
public class FieldErrorMessage {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 错误信息
     */
    private String errorMessage;

    public FieldErrorMessage() {
    }

    public FieldErrorMessage(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }
}
