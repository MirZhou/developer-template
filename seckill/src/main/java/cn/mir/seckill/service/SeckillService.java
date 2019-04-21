package cn.mir.seckill.service;

import cn.mir.seckill.dto.Exposer;
import cn.mir.seckill.dto.SecKillExecution;
import cn.mir.seckill.entity.Seckill;
import cn.mir.seckill.exception.RepeatKillException;
import cn.mir.seckill.exception.SecKillClosedException;
import cn.mir.seckill.exception.SecKillException;

import java.util.List;

/**
 * 业务接口：秒杀
 * <p>Create time: 2019/4/16 9:43 PM</p>
 *
 * @author 周光兵
 */
public interface SeckillService {
    /**
     * 查询所有秒杀商品
     *
     * @return 秒杀商品列表
     */
    List<Seckill> findSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId 秒杀商品ID
     * @return 秒杀商品
     */
    Seckill getByid(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     *
     * @param seckillId 秒杀商品ID
     * @return 秒杀接口地址DTO
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     *
     * @param secKillId 秒杀商品ID
     * @param userPhone 用户手机号
     * @param md5       加密（用于验证）
     * @return 秒杀执行结果
     */
    SecKillExecution executeSecKill(long secKillId, String userPhone, String md5)
            throws SecKillException, RepeatKillException, SecKillClosedException;

    /**
     * 执行秒杀
     *
     * @param secKillId 秒杀商品ID
     * @param userPhone 用户手机号
     * @param md5       加密（用于验证）
     * @return 秒杀执行结果
     */
    SecKillExecution executeSecKillProcedure(long secKillId, String userPhone, String md5);
}