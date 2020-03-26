package cn.mir.snapped.goods.mapper.generator;

import cn.mir.snapped.goods.entity.PurchaseRecord;
import cn.mir.snapped.goods.entity.PurchaseRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface PurchaseRecordMapper {
    long countByExample(PurchaseRecordExample example);

    int deleteByExample(PurchaseRecordExample example);

    @Delete({
        "delete from t_purchase_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_purchase_record (user_id, product_id, ",
        "price, quantity, ",
        "sum, purchase_date, ",
        "note)",
        "values (#{userId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, #{quantity,jdbcType=INTEGER}, ",
        "#{sum,jdbcType=DECIMAL}, #{purchaseDate,jdbcType=TIMESTAMP}, ",
        "#{note,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PurchaseRecord record);

    int insertSelective(PurchaseRecord record);

    List<PurchaseRecord> selectByExampleWithRowbounds(PurchaseRecordExample example, RowBounds rowBounds);

    List<PurchaseRecord> selectByExample(PurchaseRecordExample example);

    @Select({
        "select",
        "id, user_id, product_id, price, quantity, sum, purchase_date, note",
        "from t_purchase_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("cn.mir.snapped.goods.mapper.generator.PurchaseRecordMapper.BaseResultMap")
    PurchaseRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PurchaseRecord record, @Param("example") PurchaseRecordExample example);

    int updateByExample(@Param("record") PurchaseRecord record, @Param("example") PurchaseRecordExample example);

    int updateByPrimaryKeySelective(PurchaseRecord record);

    @Update({
        "update t_purchase_record",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "product_id = #{productId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "sum = #{sum,jdbcType=DECIMAL},",
          "purchase_date = #{purchaseDate,jdbcType=TIMESTAMP},",
          "note = #{note,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PurchaseRecord record);
}