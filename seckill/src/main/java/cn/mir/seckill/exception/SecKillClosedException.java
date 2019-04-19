package cn.mir.seckill.exception;

/**
 * 秒杀关闭异常
 * <p>Create time: 2019/4/16 10:01 PM</p>
 *
 * @author 周光兵
 */
public class SecKillClosedException extends RuntimeException {
    public SecKillClosedException(String message) {
        super(message);
    }

    public SecKillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
