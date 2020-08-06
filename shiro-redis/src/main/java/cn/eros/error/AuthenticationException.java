package cn.eros.error;

/**
 * <p>create time：2020-08-05 11:40
 *
 * @author Eros
 */
public class AuthenticationException extends Exception {
    public AuthenticationException() {
        this("权限不足");
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
