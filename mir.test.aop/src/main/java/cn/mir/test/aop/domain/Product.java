package cn.mir.test.aop.domain;

import lombok.Data;

/**
 * 商品
 * <p>Create time: 2019/4/9 10:22 PM</p>
 *
 * @author 周光兵
 */
@Data
public class Product {
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
}
