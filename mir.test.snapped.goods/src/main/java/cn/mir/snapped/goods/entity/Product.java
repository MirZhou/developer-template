package cn.mir.snapped.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 产品信息表
 * <p>Table name:t_product</p>
 * <p>Create time:2020-03-27 01:14:06</p>
 *
 * @author ErosZhou
 */
@Data
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

    public Product withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Product withProductName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public Product withStock(Integer stock) {
        this.setStock(stock);
        return this;
    }

    public Product withPrice(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public Product withVersion(Integer version) {
        this.setVersion(version);
        return this;
    }

    public Product withNote(String note) {
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
        sb.append(", productName=").append(productName);
        sb.append(", stock=").append(stock);
        sb.append(", price=").append(price);
        sb.append(", version=").append(version);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}