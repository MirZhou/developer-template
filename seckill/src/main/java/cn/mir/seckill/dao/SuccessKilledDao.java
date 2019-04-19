package cn.mir.seckill.dao;

import cn.mir.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>Create time: 2019/4/16 2:39 PM</p>
 *
 * @author 周光兵
 */
@Component
@Mapper
public interface SuccessKilledDao {
    /**
     * 插入购买明细,可过滤重复
     *
     * @param successKilled
     * @return 返回受影响的行数
     */
    int insertSuccessKilled(SuccessKilled successKilled);

    /**
     * 根据ID查询实体，并查询其对应的Seckill对象
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled getByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);
}