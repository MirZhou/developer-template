package cn.mir.snapped.goods.mapper.generator;

import cn.mir.snapped.goods.entity.Product;
import cn.mir.snapped.goods.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    @Delete({
        "delete from t_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_product (product_name, stock, ",
        "price, version, ",
        "note)",
        "values (#{productName,jdbcType=VARCHAR}, #{stock,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, #{version,jdbcType=INTEGER}, ",
        "#{note,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExampleWithRowbounds(ProductExample example, RowBounds rowBounds);

    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "id, product_name, stock, price, version, note",
        "from t_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("cn.mir.snapped.goods.mapper.generator.ProductMapper.BaseResultMap")
    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update t_product",
        "set product_name = #{productName,jdbcType=VARCHAR},",
          "stock = #{stock,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "version = #{version,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}