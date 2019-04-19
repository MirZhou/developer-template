package cn.mir.seckill.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>Create time: 2019/4/16 2:31 PM</p>
 *
 * @author 周光兵
 */
@Data
public class SuccessKilled {
    private Long seckillId;
    private String userPhone;
    private Integer state;
    private LocalDateTime createTime;
    private Seckill seckill;
}
