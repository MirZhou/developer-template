package cn.mir.seckill.dto;

import cn.mir.seckill.entity.SuccessKilled;
import cn.mir.seckill.enums.SecKillStateEnum;
import lombok.Data;

/**
 * 封装秒杀执行后的结果
 * <p>Create time: 2019/4/16 9:52 PM</p>
 *
 * @author 周光兵
 */
@Data
public class SecKillExecution {
    /**
     * 秒杀商品ID
     */
    private long secKillId;
    /**
     * 秒杀结果状态
     */
    private int state;
    /**
     * 状态表示
     */
    private String stateInfo;
    /**
     * 秒杀成功对象
     */
    private SuccessKilled successKilled;

    public SecKillExecution() {
    }

    public SecKillExecution(long secKillId, SecKillStateEnum stateEnum) {
        this.secKillId = secKillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public SecKillExecution(long secKillId, SecKillStateEnum stateEnum, SuccessKilled successKilled) {
        this.secKillId = secKillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }
}
