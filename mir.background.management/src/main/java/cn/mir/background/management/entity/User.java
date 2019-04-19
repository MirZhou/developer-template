package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 实体：用户
 * <p>Create time: 2019/3/23 7:35 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_user")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '用户id'")
    private Integer id;
    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '用户编号'")
    private String code;
    /**
     * 用户登录名
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '用户登录名'")
    private String username;
    /**
     * 用户真实姓名
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '用户真实姓名'")
    private String realName;
    /**
     * 默认角色ID
     */
    @Column(columnDefinition = "INT COMMENT '默认角色ID'")
    private Integer roleId;
    /**
     * 所属公司ID
     */
    @Column(columnDefinition = "INT COMMENT '所属公司ID'")
    private Integer companyId;
    /**
     * 所属公司名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '所属公司名称'")
    private String companyName;
    /**
     * 所属部门ID
     */
    @Column(columnDefinition = "INT COMMENT '所属部门ID'")
    private Integer departmentId;
    /**
     * 所属部门名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '所属部门名称'")
    private String departmentName;
    /**
     * 所属工作组ID
     */
    @Column(columnDefinition = "INT COMMENT '所属工作组ID'")
    private Integer workGroundId;
    /**
     * 所属工作组名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '所属工作组名称'")
    private String workGroundName;
    /**
     * 性别
     */
    @Column(columnDefinition = "VARCHAR(2) COMMENT '性别'")
    private String gender;
    /**
     * 手机号
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '手机号'")
    private String mobile;
    /**
     * QQ号码
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT 'QQ号码'")
    private String qq;
    /**
     * 邮箱
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '邮箱'")
    private String email;
    /**
     * 出生日期
     */
    @Column(columnDefinition = "DATE COMMENT '出生日期'")
    private LocalDate birthday;
    /**
     * 岗位
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '岗位'")
    private String duty;
    /**
     * 职称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '职称'")
    private String title;
    /**
     * 用户登录密码
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '用户登录密码'")
    private String password;
    /**
     * 登录次数
     */
    @Column(columnDefinition = "INT DEFAULT 0 COMMENT '登录次数'")
    private Integer loginCount;
    /**
     * 第一次访问时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '第一次访问时间'")
    private LocalDateTime firstVisit;
    /**
     * 上一次访问时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '上一次访问时间'")
    private LocalDateTime previousVisit;
    /**
     * 最后访问时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '最后访问时间'")
    private LocalDateTime lastVisit;
    /**
     * 是否职员。1：职员；0：非职员
     */
    @Column(columnDefinition = "INT DEFAULT 1 COMMENT '是否职员。1：职员；0：非职员'")
    private Integer isStaff;
    /**
     * 用户头像
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '用户头像'")
    private String avatar;
    /**
     * 标识是否启用。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    private Integer markEnable;
    /**
     * 用户来源。1：创建；2：注册
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '用户来源。1：创建；2：注册'")
    private Integer userSource;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String description;
}