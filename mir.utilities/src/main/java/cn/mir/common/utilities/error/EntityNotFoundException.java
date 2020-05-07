package cn.mir.common.utilities.error;

/**
 * 异常：未找到实体
 *
 * @author 周光兵
 */
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6908239124368353168L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}