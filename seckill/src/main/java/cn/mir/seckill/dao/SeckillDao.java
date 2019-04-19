package cn.mir.seckill.dao;

import cn.mir.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 秒杀商品Dao
 * <p>Create time: 2019/4/16 2:34 PM</p>
 *
 * @author 周光兵
 */
@Component
@Mapper
public interface SeckillDao {
    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return 返回受影响的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") LocalDateTime killTime);

    /**
     * 根据ID查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> findAll(@Param("offset") int offset, @Param("limit") int limit);
}