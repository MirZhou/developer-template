package cn.mir.snapped.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 产品信息表
 * <p>Table name:t_product</p>
 * <p>Create time:2020-03-30 23:32:18</p>
 *
 * @author ErosZhou
 */
@Data
@Accessors(chain = true)
public class Product implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", stock=").append(stock);
        sb.append(", price=").append(price);
        sb.append(", version=").append(version);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}