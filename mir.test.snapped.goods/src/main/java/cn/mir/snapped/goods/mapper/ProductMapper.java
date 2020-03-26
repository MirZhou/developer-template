package cn.mir.snapped.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Mapper接口：产品信息
 * <p>
 * Create time:2020-03-27 00:43:58
 * </p>
 * 
 * @author ErosZhou
 */
@Component
@Mapper
public interface ProductMapper extends cn.mir.snapped.goods.mapper.generator.ProductMapper {
    /**
     * 减库存
     * 
     * @param id       产品ID
     * @param quantity 扣减数量
     * @return 受影响的行数
     */
    @Update(value = "UPDATE t_product SET stock = stock - #{quantity} WHERE id = #{id}")
    int decreaseProduct(@Param("id") Integer id, @Param("quantity") int quantity);

}