package cn.mir.common.utilities.error;

/**
 * 异常：凭据无效
 * <p>Create time: 2019-08-29 18:10</p>
 *
 * @author 周光兵
 */
public class IncorrectCredentialsException extends RuntimeException {

    public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
