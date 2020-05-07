package cn.mir.common.utilities.error;

/**
 * 异常：参数错误
 *
 * @author 周光兵
 */
public class ParamErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}