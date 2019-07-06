package cn.mir.common.utilities;

import lombok.Data;

import java.io.Serializable;

/**
 * 当前操作员的简要信息表
 * <p>Create time: 2019/3/19 1:42 AM</p>
 *
 * @author 周光兵
 */
@Data
public class BaseUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 操作员代码
     */
    private Integer id;
    /**
     * 操作员的用户名
     */
    private String username;
    /**
     * 操作员姓名
     */
    private String realName;
    /**
     * 当前组织结构部门代码
     */
    private Integer departmentId;
    /**
     * 当前组织结构编号
     */
    private String departmentCode;
    /**
     * 当前组织结构部门名称
     */
    private String departmentFullName;
    /**
     * 当前组织结构公司代码
     */
    private Integer companyId;
    /**
     * 当前组织结构公司编号
     */
    private String companyCode;
    /**
     * 当前组织结构公司名称
     */
    private String companyFullName;
    /**
     * 操作员类型
     */
    private String operatorCategory;
    /**
     * 操作员类型名称
     */
    private String operatorCategoryFullName;
    /**
     * IP地址
     */
    private String iPAddress;
    /**
     * MAC地址
     */
    private String macAddress;
    /**
     * 当前语言选择
     */
    private String currentLanguage;
    /**
     * 当前布局风格
     */
    private String themes;
    /**
     * 描述
     */
    private String description;
    /**
     * WebService 用户名
     */
    private String webServiceUsername;
    /**
     * WebService 密码
     */
    private String webServicePassword;
}