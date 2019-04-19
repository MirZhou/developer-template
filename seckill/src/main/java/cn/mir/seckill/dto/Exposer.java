package cn.mir.seckill.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 暴露秒杀地址DTO
 * <p>Create time: 2019/4/16 9:47 PM</p>
 *
 * @author 周光兵
 */
@Data
public class Exposer {
    /**
     * 是否开启秒杀
     */
    private boolean exposed;
    /**
     * 一种加密措施
     */
    private String md5;
    /**
     * 秒杀商品ID
     */
    private long seckillId;
    /**
     * 系统时间
     */
    private LocalDateTime now;
    /**
     * 秒杀开始时间
     */
    private LocalDateTime start;
    /**
     * 秒杀结束时间
     */
    private LocalDateTime end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, LocalDateTime now, LocalDateTime start, LocalDateTime end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }
}
