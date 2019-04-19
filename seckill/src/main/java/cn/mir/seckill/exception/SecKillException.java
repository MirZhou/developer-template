package cn.mir.seckill.exception;

/**
 * 秒杀相关业务异常
 * <p>Create time: 2019/4/16 10:02 PM</p>
 *
 * @author 周光兵
 */
public class SecKillException extends RuntimeException {
    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
