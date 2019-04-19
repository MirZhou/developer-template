package cn.mir.seckill.enums;

/**
 * 枚举：秒杀状态
 * <p>Create time: 2019/4/17 9:10 PM</p>
 *
 * @author 周光兵
 */
public enum SecKillStateEnum {
    /**
     * 秒杀成功
     */
    SUCCESS(1, "秒杀成功"),
    /**
     * 秒杀结束
     */
    END(0, "秒杀结束"),
    /**
     * 重复秒杀
     */
    REPEAT_KILL(-1, "重复秒杀"),
    /**
     * 系统异常
     */
    INNER_ERROR(-2, "系统异常"),
    /**
     * 数据篡改
     */
    DATA_REWRITE(-3, "数据篡改");

    /**
     * 状态码
     */
    private int state;
    /**
     * 状态码描述
     */
    private String stateInfo;

    /**
     * 枚举：秒杀状态
     *
     * @param state     状态码
     * @param stateInfo 状态码描述
     */
    SecKillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SecKillStateEnum stateOf(int index) {
        for (SecKillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}