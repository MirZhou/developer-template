package cn.mir.background.management.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 实体：用户登录日志
 * <p>Create time: 2019/3/24 10:16 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table
@Data
public class OperationLog {
    /**
     * 记录ID
     */
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid2")
    @GeneratedValue(generator = "systemUUID")
    @Column(columnDefinition = "VARCHAR(36) COMMENT '记录ID'")
    private String id;
    /**
     * 登录用户ID
     */
    @Column(columnDefinition = "INT NOT NULL COMMENT '登录用户ID'")
    private Integer userId;
    /**
     * 登录用户真实姓名
     */
    @Column(columnDefinition = "VARCHAR(50) NOT NULL COMMENT '登录用户真实姓名'")
    private String realName;
    /**
     * 当前组织结构部门代码
     */
    @Column(columnDefinition = "INT COMMENT '当前组织结构部门代码'")
    private Integer departmentId;
    /**
     * 所属部门名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '当前组织结构部门名称'")
    private String departmentName;
    /**
     * 当前组织结构公司代码
     */
    @Column(columnDefinition = "INT COMMENT '当前组织结构公司代码'")
    private Integer companyId;
    /**
     * 所属公司名称
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '当前组织结构公司名称'")
    private String companyName;
    /**
     * IP地址
     */
    @Column(columnDefinition = "VARCHAR(20) COMMENT 'IP地址'")
    private String iPAddress;
    /**
     * 访问时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '访问时间'")
    private LocalDateTime visitTime;
    /**
     * 访问模块
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '访问模块'")
    private String moduleName;
    /**
     * 执行操作
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '执行操作'")
    private String operational;
    /**
     * 备注说明
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '备注说明'")
    private String description;
}