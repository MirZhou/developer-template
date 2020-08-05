package cn.mir.spring.generator.enums;

/**
 * 枚举：分类
 * <p>create time：2020-08-04 19:46
 *
 * @author Eros
 */
public enum CategoryEnum implements IMybatisEnum {
    /**
     * 分类一
     */
    CATEGORY_ONE(1, "分类一"),
    /**
     * 分类二
     */
    CATEGORY_TWO(2, "分类二"),
    /**
     * 分类三
     */
    CATEGORY_THREE(3, "分类三"),
    /**
     * 分类四
     */
    CATEGORY_FOUR(4, "分类四");

    private final int code;
    private final String statusInfo;

    CategoryEnum(int code, String statusInfo) {
        this.code = code;
        this.statusInfo = statusInfo;
    }

    public int getCode() {
        return code;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    @Override
    public int getValue() {
        return this.getCode();
    }
}
