package cn.mir.spring.generator.mapper;

import cn.mir.spring.generator.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>create time：2020-08-04 19:24
 *
 * @author Eros
 */
@Component
@Mapper
public interface ProductMapper {
    @Insert({
            "insert into t_product (product_name, stock, ",
            "price, version, note, `status`, category)",
            "values (#{entity.productName} , #{entity.stock} , ",
            "#{entity.price}, #{entity.version}, #{entity.note}, #{entity.status}, #{entity.category}  )"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(@Param("entity") Product entity);

    @Select("SELECT * FROM t_product")
    List<Product> selectAll();
}
