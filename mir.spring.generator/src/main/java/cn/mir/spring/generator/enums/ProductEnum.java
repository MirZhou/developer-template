package cn.mir.spring.generator.enums;

/**
 * 枚举：商品状态
 * <p>create time：2020-08-04 17:20
 *
 * @author Eros
 */
public enum ProductEnum implements IMybatisEnum {
    /**
     * 未上架
     */
    NOT_ON_SHELF(1, "未上架"),
    /**
     * 已上架
     */
    ON_MARKET(2, "已上架"),
    /**
     * 已下架/停产
     */
    NO_LONGER_SOLD(3, "已下架/停产");

    private final int status;
    private final String statusInfo;

    ProductEnum(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    @Override
    public int getValue() {
        return this.getStatus();
    }
}
