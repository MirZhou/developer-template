package cn.mir.snapped.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 购买记录表
 * <p>Table name:t_purchase_record</p>
 * <p>Create time:2020-03-27 01:14:06</p>
 *
 * @author ErosZhou
 */
@Data
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

    public PurchaseRecord withId(Integer id) {
        this.setId(id);
        return this;
    }

    public PurchaseRecord withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    public PurchaseRecord withProductId(Integer productId) {
        this.setProductId(productId);
        return this;
    }

    public PurchaseRecord withPrice(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public PurchaseRecord withQuantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public PurchaseRecord withSum(BigDecimal sum) {
        this.setSum(sum);
        return this;
    }

    public PurchaseRecord withPurchaseDate(LocalDateTime purchaseDate) {
        this.setPurchaseDate(purchaseDate);
        return this;
    }

    public PurchaseRecord withNote(String note) {
        this.setNote(note);
        return this;
    }

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