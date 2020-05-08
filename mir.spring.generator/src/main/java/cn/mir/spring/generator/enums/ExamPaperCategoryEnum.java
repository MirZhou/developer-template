package cn.mir.spring.generator.enums;

/**
 * 枚举：用户分类
 * <p>
 * Create time:2020-05-08 10:26:03
 * </p>
 * 
 * @author Eros Zhou
 */
public enum ExamPaperCategoryEnum {

    /**
     * 普通用户
     */
    NORMAL(1, "试卷"),
    /**
     * 超级管理员
     */
    SUPER_ADMINISTRATOR(2, "超级管理员");

    /**
     * 状态
     */
    private int status;
    /**
     * 状态说明
     */
    private String statusInfo;

    ExamPaperCategoryEnum(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public static ExamPaperCategoryEnum statusOf(int index) {
        for (ExamPaperCategoryEnum status : values()) {
            if (status.getStatus() == index) {
                return status;
            }
        }

        return null;
    }
}