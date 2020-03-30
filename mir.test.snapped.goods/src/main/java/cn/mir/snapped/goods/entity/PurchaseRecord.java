package cn.mir.snapped.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 购买记录表
 * <p>Table name:t_purchase_record</p>
 * <p>Create time:2020-03-30 23:32:18</p>
 *
 * @author ErosZhou
 */
@Data
@Accessors(chain = true)
public class PurchaseRecord implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 总价
     */
    private BigDecimal sum;

    /**
     * 购买日期
     */
    private LocalDateTime purchaseDate;

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
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", sum=").append(sum);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}