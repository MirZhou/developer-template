package cn.mir.background.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 实体：工作流当前状态
 * <p>Create time: 2019/4/3 11:35 PM</p>
 *
 * @author 周光兵
 */
@Entity
@Table(name = "base_workflow_current_status")
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkflowCurrentStatus extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT '主键ID'")
    private Integer id;
    /**
     * 所属工作流ID
     */
    @Column(columnDefinition = "INT COMMENT '所属工作流ID'")
    private Integer workflowId;
    /**
     * 所属工作流步骤ID
     */
    @Column(columnDefinition = "INT COMMENT '所属工作流步骤ID'")
    private Integer workflowStepId;
    /**
     * 所属工作流步骤
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '所属工作流步骤'")
    private String workflowStepName;
    /**
     * 待审核部门ID
     */
    @Column(columnDefinition = "INT COMMENT '待审核部门ID'")
    private Integer pendingDepartmentId;
    /**
     * 待审核部门
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '待审核部门'")
    private String pendingDepartmentName;
    /**
     * 待审核用户ID
     */
    @Column(columnDefinition = "INT COMMENT '待审核用户ID'")
    private Integer pendingUserId;
    /**
     * 待审核用户
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '待审核用户'")
    private String pendingUserName;
    /**
     * 待审核角色ID
     */
    @Column(columnDefinition = "INT COMMENT '待审核角色ID'")
    private Integer pendingRoleId;
    /**
     * 待审核角色
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '待审核角色'")
    private String pendingRoleName;
    /**
     * 审核部门ID
     */
    @Column(columnDefinition = "INT COMMENT '审核部门ID'")
    private Integer auditDepartmentId;
    /**
     * 审核部门名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核部门名称'")
    private String auditDepartmentName;
    /**
     * 审核员ID
     */
    @Column(columnDefinition = "INT COMMENT '审核员ID'")
    private Integer auditorId;
    /**
     * 审核员名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核员名称'")
    private String auditorName;
    /**
     * 审核角色ID
     */
    @Column(columnDefinition = "INT COMMENT '审核角色ID'")
    private Integer auditRoleId;
    /**
     * 审核角色名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核角色名称'")
    private String auditRoleName;
    /**
     * 发出时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '发出时间'")
    private LocalDateTime sendTime;
    /**
     * 审核时间
     */
    @Column(columnDefinition = "DATETIME(6) COMMENT '审核时间'")
    private LocalDateTime auditTime;
    /**
     * 审核意见
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '审核意见'")
    private String auditOpinion;
    /**
     * 审核状态码
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '审核状态码'")
    private String auditStatusCode;
    /**
     * 排序值
     */
    @Column(columnDefinition = "INT COMMENT '排序值'")
    private Integer sortValue;
    /**
     * 标识是否启用。1：启用；0；禁用
     */
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1 COMMENT '标识是否启用。1：启用；0：禁用'")
    private Integer markEnable;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '备注'")
    private String description;
}