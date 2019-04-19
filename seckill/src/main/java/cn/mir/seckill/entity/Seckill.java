package cn.mir.seckill.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>Create time: 2019/4/16 2:28 PM</p>
 *
 * @author 周光兵
 */
@Data
public class Seckill {
    private Long seckillId;
    private String name;
    private Integer number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
}
