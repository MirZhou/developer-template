package cn.mir.spring.generator.entity;

import cn.mir.spring.generator.enums.CategoryEnum;
import cn.mir.spring.generator.enums.ProductEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>create time：2020-08-04 17:18
 *
 * @author Eros
 */
@Data
public class Product {
    private Long id;
    private String productName;
    private Integer stock;
    private BigDecimal price;
    private Long version;
    private String note;
    private ProductEnum status;
    private CategoryEnum category;
}
