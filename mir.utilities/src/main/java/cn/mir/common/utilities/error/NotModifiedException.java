package cn.mir.common.utilities.error;

/**
 * 异常：没有任何修改
 *
 * @author 周光兵
 */
public class NotModifiedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotModifiedException(String message) {
        super(message);
    }

    public NotModifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
