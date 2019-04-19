package cn.mir.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * <p>Create time: 2019/4/16 9:57 PM</p>
 *
 * @author 周光兵
 */
public class RepeatKillException extends RuntimeException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}